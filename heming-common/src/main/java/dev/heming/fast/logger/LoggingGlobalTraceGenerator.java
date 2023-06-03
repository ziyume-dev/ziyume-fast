package dev.heming.fast.logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/11/10 16:17
 */
@Component
public class LoggingGlobalTraceGenerator implements LoggingTraceGenerator {

    private static final String PROCESS_ID = UUID.randomUUID().toString().replaceAll("-", "");

    @Override
    public String generateTraceId() {
        return StringUtils.join(
                ".",
                StringUtils.substring(PROCESS_ID, 8),
                String.valueOf(Thread.currentThread().getId()),
                String.valueOf(System.currentTimeMillis())
        );
    }

}
