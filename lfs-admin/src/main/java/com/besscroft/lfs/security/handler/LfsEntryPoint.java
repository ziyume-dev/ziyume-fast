package com.besscroft.lfs.security.handler;

import com.besscroft.lfs.constant.HttpStatus;
import com.besscroft.lfs.result.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证错误处理器
 *
 * @Author Bess Croft
 * @Time 2021/7/7 16:20
 */
@Slf4j
public class LfsEntryPoint implements AuthenticationEntryPoint {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.error(e.getMessage());
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        CommonResult<String> resultVO = new CommonResult<>(HttpStatus.UNAUTHORIZED,"暂未登录或token已经过期",null);
        out.write(objectMapper.writeValueAsString(resultVO));
        out.flush();
        out.close();
    }

}
