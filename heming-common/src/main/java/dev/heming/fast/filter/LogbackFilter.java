package dev.heming.fast.filter;

import dev.heming.fast.constant.LogbackConstant;
import dev.heming.fast.logger.LoggingTraceGenerator;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description 日志过滤器
 * @Author Bess Croft
 * @Date 2022/11/10 16:06
 */
@Order(0)
@Component
@RequiredArgsConstructor
public class LogbackFilter implements Filter {

    private final LoggingTraceGenerator loggingTraceGenerator;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            MDC.put(LogbackConstant.TRACT_ID, loggingTraceGenerator.generateTraceId());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.put(LogbackConstant.TRACT_ID, "N/A");
        }
    }

}
