package com.skyscraper.engine.web.engineweb.aspect;

import com.skyscraper.engine.service.common.BizException;
import com.skyscraper.engine.service.common.ExceptionCode;
import com.skyscraper.engine.service.common.PaasResponnse;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * create by sumerian on 2020/6/18
 * <p>
 * desc:拦截带有@RestController注解的类中的方法，添加打印日志捕获异常的功能
 **/

@Aspect
@Component
public class ControllerAspect {
    private static final String POINT_CUT = "com.skyscraper.engine.web.engineweb.aspect.ControllerAspect.myPointcut()";
    private final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    private static String METHOD_NAME = "";
    private static String CLASS_NAME = "";


    // 切入点表达式按需配置
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && execution(public * *(..))")
    private void myPointcut() {
    }


    @Before(POINT_CUT)
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        CLASS_NAME = className;
        String methodName = joinPoint.getSignature().getName();
        METHOD_NAME = methodName;
        Object[] args = joinPoint.getArgs();
        StringBuilder log = new StringBuilder();
        for (Object arg : args) {
            log.append(arg + " ");
        }
        logger.info("#clazzname:" + className + "@@method@@" + methodName + "is start..params={}", log.toString());
    }

    @AfterReturning(value = POINT_CUT, returning = "returnVal")
    public void afterReturin(Object returnVal) {
        logger.warn("#clazzname:{}   @@method@@:{}   returnVal={}", CLASS_NAME, METHOD_NAME, returnVal);
    }

    @AfterThrowing(value = POINT_CUT, throwing = "e")
    public void afterThrowing(Throwable e) {
        if (e instanceof BizException) {
            logger.error("通知中发现异常StationErrorCodeException", e);
        } else {
            logger.error("通知中发现未知异常", e);
        }
    }

    @Around(value = POINT_CUT)
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            PaasResponnse resultDTO = new PaasResponnse();
            if (e instanceof BizException) {
                BizException errorCodeException = (BizException) e;
                resultDTO.setCode(errorCodeException.getErrorCode());
                resultDTO.setMsg(errorCodeException.getErrorMsg());
            } else {
                resultDTO.setCode(ExceptionCode.ERROR.getCode());
                resultDTO.setMsg(ExceptionCode.ERROR.getName());
            }
            return resultDTO;
        }
        return result;
    }
}