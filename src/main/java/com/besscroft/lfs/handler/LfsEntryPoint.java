package com.besscroft.lfs.handler;

import com.besscroft.lfs.constant.HttpStatus;
import com.besscroft.lfs.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error(e.getMessage());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        CommonResult<String> resultVO = new CommonResult<>(HttpStatus.UNAUTHORIZED,"没有登录",null);
        out.write(resultVO.toString());
        out.flush();
        out.close();
    }

}
