package com.besscroft.lfs.security.handler;

import com.besscroft.lfs.constant.HttpStatus;
import com.besscroft.lfs.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 授权错误处理器
 *
 * @Author Bess Croft
 * @Time 2021/7/7 16:26
 */
@Slf4j
public class LfsDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.error(e.getMessage());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        CommonResult<String> resultVO = new CommonResult<>(HttpStatus.FORBIDDEN, "没有相关权限",null);
        out.write(resultVO.toString());
        out.flush();
        out.close();
    }

}
