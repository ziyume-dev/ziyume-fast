package com.besscroft.lfs.aspectj;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.besscroft.lfs.constant.AuthConstants;
import com.besscroft.lfs.dto.UserDto;
import com.besscroft.lfs.entity.WebLog;
import com.besscroft.lfs.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * web操作日志处理切面
 *
 * @Author Bess Croft
 * @Time 2021/7/24 12:27
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {

    private static final String KEY = "requestId";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    /** 换行符 */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** 用来记录请求进入的时间，防止多线程时出错，这里用了ThreadLocal */
    ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    @Autowired
    private LogService logService;

    /** 配置织入点，以自定义 @webLog 注解为切点 */
    @Pointcut("@annotation(com.besscroft.lfs.annotation.WebLog)")
    public void webLog() {

    }

    /**
     * 环绕方法
     * 执行切点后，会依次调用 @Before -> 接口逻辑代码 -> @After -> @AfterReturning
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 开始时间
        START_TIME.set(System.currentTimeMillis());
        // 执行切点
        Object result = proceedingJoinPoint.proceed();
        // 获取当前请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 创建日志对象
        WebLog webLog = new WebLog();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = "";
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                currentUserName = authentication.getName();
                // 设置操作用户
                webLog.setUsername(currentUserName);
            }
        } catch (Exception e) {
            LOGGER.error("暂未登录或token已经过期");
        }
        // 设置id
        webLog.setId(IdUtil.simpleUUID());
        // 设置日志描述信息
        webLog.setDescription(getAspectLogDescription(proceedingJoinPoint));
        // 请求地址
        webLog.setUrl(request.getRequestURL().toString());
        // 请求方法
        webLog.setHttpMethod(request.getMethod());
        // 请求方法路径:全限定名+方法名
        webLog.setClassMethod(proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." +  proceedingJoinPoint.getSignature().getName() + "()");
        // 请求者ip地址
        webLog.setIp(request.getRemoteAddr());
        // 请求入参
        webLog.setRequestArgs(JSONUtil.toJsonStr(proceedingJoinPoint.getArgs()));
        // 响应出参
        webLog.setResponseArgs(JSONUtil.toJsonStr(result));
        // 请求时间
        webLog.setStartTime(LocalDateTime.now());
        // 消耗时间
        webLog.setSpendTime(System.currentTimeMillis() - START_TIME.get());
        // 打印响应参数
        LOGGER.info("Response Args:{}", JSONUtil.toJsonStr(result));
        // 执行时间
        LOGGER.info("Time Consuming:{}", System.currentTimeMillis() - START_TIME.get());
        // 将日志信息存入数据库
        logService.saveWebLog(webLog);
        // 出口移除请求ID
        MDC.remove(KEY);
        return result;
    }

    /**
     * 在切点之前织入
     * @param joinPoint
     */
    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Exception {
        // 入口传入请求ID
        MDC.put(KEY, UUID.randomUUID().toString());
        // 获取当前请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        LOGGER.info("================== Start ==================");
        // 打印请求 url
        LOGGER.info("URL            :{}", request.getRequestURL().toString());
        // 打印描述信息
        LOGGER.info("Description    :{}", methodDescription);
        // 打印 Http method
        LOGGER.info("HTTP Method    :{}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        LOGGER.info("Class Method   :{}.{}()", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        LOGGER.info("IP             :{}", request.getRemoteAddr());
        // 打印请求入参
        LOGGER.info("Request Args   :{}", JSONUtil.toJsonStr(joinPoint.getArgs()));
    }

    /**
     * 在切点之后织入
     */
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        LOGGER.info("================== End ==================" + LINE_SEPARATOR);
    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(com.besscroft.lfs.annotation.WebLog .class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

}

