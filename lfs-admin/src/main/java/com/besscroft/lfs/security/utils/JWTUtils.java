package com.besscroft.lfs.security.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt 工具类
 *
 * @Author Bess Croft
 * @Time 2021/7/7 16:07
 */
@Slf4j
@Component
public class JWTUtils {

    private static final String CLAIM_KEY_USERNAME = "username";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    /**
     * 根据负责生成 JWT 的 token
     */
    public String generateToken(@NonNull Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(secret)
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(secretKey)
                .compact();
    }

    /**
     * 从 token 中获取 JWT 中的负载
     */
    public Claims getClaimsFromToken(@NonNull String token) {
        // 如果是空字符串直接返回 null
        if (!StringUtils.hasLength(token)) {
            return null;
        }

        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token 过期、token 非法都会导致解析失败
        try {
            claims = Jwts.parserBuilder()
                    .requireSubject(secret)
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AuthenticationServiceException("token 解析失败！");
        }
        return claims;
    }

    /**
     * 从 token 中获取登录用户名
     */
    public String getUserNameFromToken(@NonNull String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(CLAIM_KEY_USERNAME);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证 token 是否还有效
     *
     * @param token       客户端传入的 token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(@NonNull String token, @NonNull UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断 token 是否已经失效
     */
    private boolean isTokenExpired(@NonNull String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从 token 中获取过期时间
     */
    private Date getExpiredDateFromToken(@NonNull String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成 token
     */
    public String generateToken(@NonNull UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 当原来的 token 没过期时是可以刷新的
     *
     * @param oldToken 带 tokenHead 的 token
     */
    public String refreshHeadToken(@NonNull String oldToken) {
        if (StrUtil.isEmpty(oldToken)) {
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        // token 校验不通过
        Claims claims = getClaimsFromToken(token);
        if (claims==null) {
            return null;
        }
        // 如果 token 已经过期，不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }
        // 如果 token 在 30 分钟之内刚刷新过，返回原 token
        if (tokenRefreshJustBefore(token,30*60)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断 token 在指定时间内是否刚刚刷新过
     * @param token 原 token
     * @param time 指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(@NonNull String token, @NonNull int time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        // 刷新时间在创建时间的指定时间内
        if (refreshDate.after(created)&&refreshDate.before(DateUtil.offsetSecond(created,time))) {
            return true;
        }
        return false;
    }

}
