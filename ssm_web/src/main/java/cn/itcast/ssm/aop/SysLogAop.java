package cn.itcast.ssm.aop;

import cn.itcast.ssm.domain.SysLog;
import cn.itcast.ssm.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class SysLogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService service;
    //定义环绕通知
    @Around("execution(* cn.itcast.ssm.controller.*.*(..))")
    public Object saveLog(ProceedingJoinPoint joinPoint){
        /**
         * 主键 无意义uuid
         * 访问时间
         * 操作者用户名
         * 访问ip
         * 访问资源url
         * 执行时长
         * 访问方法
         */

        try {
            //访问时间
            Date visitTime = new Date();
            //操作者用户名
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            long start = System.currentTimeMillis();
            Object proceed = joinPoint.proceed();
            long end = System.currentTimeMillis();
            //访问时长
            long executionTime = end - start;
            //访问ip
            String ip = request.getRemoteAddr();
            String url = request.getRequestURI();
            //访问方法
            //获取切入点方法所在类的全类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取切入点方法名
            String methodName = joinPoint.getSignature().getName();
            String method = className +"."+methodName;
            SysLog sysLog = new SysLog();
            sysLog.setVisitTime(visitTime);
            sysLog.setUsername(username);
            sysLog.setIp(ip);
            sysLog.setUrl(url);
            sysLog.setExecutionTime(executionTime);
            sysLog.setMethod(method);
            System.out.println(sysLog);
            service.save(sysLog);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }
}
