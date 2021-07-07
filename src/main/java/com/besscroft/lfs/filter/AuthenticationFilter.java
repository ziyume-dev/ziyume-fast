package com.besscroft.lfs.filter;

import com.besscroft.lfs.service.impl.UserServiceImpl;
import com.besscroft.lfs.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 *
 * @Author Bess Croft
 * @Time 2021/7/7 16:04
 */
@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("--- AuthenticationFilter begin ---");
        // 从请求头中获取token字符串并解析
        Claims claims = jwtUtils.getClaimsFromToken(request.getHeader("Authorization"));
        if (claims != null) {
            String username = claims.getSubject();
            UserDetails user = userService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        log.info("--- AuthenticationFilter end ---");
        filterChain.doFilter(request, response);
    }

}
