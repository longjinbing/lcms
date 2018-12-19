package com.ljb.interceptor;

import com.ljb.annotion.MenuDescription;
import com.ljb.dao.LogDao;
import com.ljb.entity.Log;
import com.ljb.security.IdentityUtils;
import com.ljb.utils.RequestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/15<br>
 * 描述: <br>
 */
@Aspect
public class SysLogAop {
    //获取开始时间
    private long BEGIN_TIME ;

    //获取结束时间
    private long END_TIME;
    @Autowired
    private LogDao sysLogDao;

    //定义本次log实体
    private Log log = new Log();

    @Pointcut("execution(* com.ljb.controller..*.*(..))")
    private void controllerAspect(){}

    /**
     * 方法开始执行
     */
    @Before("controllerAspect()")
    public void doBefore(){
        BEGIN_TIME = new Date().getTime();
        if(IdentityUtils.isAuthenticated()){
            log.setCreateId(IdentityUtils.getUserId());
            log.setRemark("系统用户");
        }else{
            log.setRemark("未知用户");
        }
    }

    /**
     * 方法结束执行
     */
    @After("controllerAspect()")
    public void after(){
        END_TIME = new Date().getTime();
    }

    /**
     * 方法结束执行后的操作
     */
    @AfterReturning("controllerAspect()")
    public void doAfter(){

    }

    /**
     * 方法有异常时的操作
     */
    @AfterThrowing("controllerAspect()")
    public void doAfterThrow(){
        try {
            sysLogDao.insert(log);
        }catch (Exception e){

        }
    }


    /**
     * 方法执行
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        //日志实体对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        Object object = null;
        Method method = null;
        log.setParams(args.toString());
        log.setMethod(methodName);
        log.setIp(RequestUtils.getIp(request));
        log.setUrl(request.getRequestURI());
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            log.setOperation("未知方法");
            log.setLevel(2);
            log.setErrorStack(e1.getStackTrace().toString());
        }
        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
            if (method.isAnnotationPresent(MenuDescription.class)) {
                MenuDescription desc = method.getAnnotation(MenuDescription.class);
                try {
                    object = pjp.proceed();
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    //获取当前登陆用户信息
                    log.setOperation(desc.name());
                    log.setLevel(1);
                    log.setErrorStack(e.getStackTrace().toString());
                }
            } else {//没有包含注解
                object = pjp.proceed();
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }
}
