package com.ljb.interceptor;

import com.alibaba.fastjson.JSON;
import com.ljb.dao.LogDao;
import com.ljb.entity.Log;
import com.ljb.security.IdentityUtils;
import com.ljb.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/14<br>
 * 描述: <br>
 */
public class SysLogInterceptor implements HandlerInterceptor {

    @Autowired
    private LogDao sysLogDao;
    private Log sysLog;


    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("ThreadLocal StartTime");

    private String getParamString(Map<String, String[]> map) {
        return JSON.toJSONString(map);
    }

    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Throwable e) {
        if (e == null){
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 该方法将在请求处理之前进行调用。
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        startTimeThreadLocal.set(startTime);		//线程绑定变量（该数据只有当前请求的线程可见）
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("-----------------------开始计时:").append(new SimpleDateFormat("hh:mm:ss.SSS").format(startTime)).append("-------------------------------------\n");
            HandlerMethod h = (HandlerMethod) handler;
            sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
            sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
            sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
            sb.append("URI       : ").append(request.getRequestURI()).append("\n");
            if(h.getMethod().getName().equals("delete")||h.getMethod().getName().equals("update")||h.getMethod().getName().equals("save")) {
                sysLog = new Log();
                sysLog.setOperation(request.getRequestURI().replace("/platform/", ""));
                sysLog.setMethod(h.getMethod().getName());
                sysLog.setParams(getParamString(request.getParameterMap()));
                sysLog.setIp(RequestUtils.getIp(request));
                if (IdentityUtils.isAuthenticated()) {
                    sysLog.setCreateId(IdentityUtils.getUserId());
                }
                sysLogDao.insert(sysLog);
            }
        }

        return true;
    }

    /**
     * 在当前请求进行处理之后，也就是Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图返回渲染之前被调用，所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if(handler instanceof HandlerMethod){
            StringBuilder sb = new StringBuilder(1000);
            sb.append("CostTime  : ").append(executeTime).append("ms").append("\n");
            sb.append("-------------------------------------------------------------------------------");
        }
    }


    /**
     * 该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
