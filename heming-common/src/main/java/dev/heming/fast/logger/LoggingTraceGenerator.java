package dev.heming.fast.logger;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/11/10 16:15
 */
public interface LoggingTraceGenerator {

    /**
     * 生成 traceId
     * @return traceId
     */
    String generateTraceId();

}
