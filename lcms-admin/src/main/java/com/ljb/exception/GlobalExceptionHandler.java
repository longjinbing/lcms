package com.ljb.exception;

import com.alibaba.fastjson.JSON;
import com.ljb.dao.LogDao;
import com.ljb.entity.Log;
import com.ljb.security.IdentityUtils;
import com.ljb.utils.ExceptionUtils;
import com.ljb.utils.R;
import com.ljb.utils.RequestUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static org.apache.commons.logging.Log logger = LogFactory.getLog(GlobalExceptionHandler.class);
    @Autowired
    private LogDao logDao;
    @Autowired
    private HttpServletRequest httpServletRequest;


    /**
     * 统一处理参数绑定错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, Object> attrMap = new HashMap<String, Object>();
        for (FieldError error : fieldErrors) {
            attrMap.put(error.getField(), error.getDefaultMessage());
        }
        recodError( e.getStackTrace(),"数据绑定失败",e.getParameter().toString(),1);
        return R.error(403, "数据校验失败").put("data", attrMap);
    }

    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public R appException(AppException e) {
        String message=e.getHttpStatus()==HttpStatus.UNAUTHORIZED?"用户未登录或登录凭证过期":e.getHttpStatus()==HttpStatus.FORBIDDEN?"操作失败":"风险操作：终止此次操作";
        return R.error(e.getHttpStatus().value(), message);
    }

    @ExceptionHandler(value = KaptchaException.class)
    public R kaptchaExceptionHandler(KaptchaException kaptchaException) {
        if (kaptchaException instanceof KaptchaIncorrectException) {
            return R.error(400, "验证码不正确");
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            return R.error(400, "验证码未找到");
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            return R.error(400,"验证码过期");
        } else {
            return R.error(400, "验证码渲染失败");
        }
    }



    //参数类型不匹配
//getPropertyName()获取数据类型不匹配参数名称
//getRequiredType()实际要求客户端传递的数据类型
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R requestTypeMismatch(TypeMismatchException e){
        recodError( e.getStackTrace(),"数据类型错误",e.getPropertyName(),1);
        return R.error(402, "参数类型不匹配,参数" + e.getPropertyName() + "类型应该为" + e.getRequiredType());
    }
    //缺少参数异常
//getParameterName() 缺少的参数名称
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R requestMissingServletRequest(MissingServletRequestParameterException e){
        recodError( e.getStackTrace(),"缺少参数:",e.getParameterName().toString(),1);
        return R.error(402, "缺少必要参数,参数名称为" + e.getParameterName());
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R noHandlerFound(HttpMessageNotReadableException e){
        recodError( e.getStackTrace(),"访问路径不存在",null,2);
        return R.error(404, "访问失败，页面找不到");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R httpMessageNotReadable(HttpMessageNotReadableException e){
        recodError( e.getStackTrace(),"访问路径不存在",null,2);
        return R.error(404, "访问失败，页面找不到");
    }

    @ExceptionHandler({UnexpectedTypeException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R paramsError(UnexpectedTypeException e){
       recodError( e.getStackTrace(),"参数类型错误",null,1);
        return R.error(402, "参数类型错误："+e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R exception(Exception e){
        recodError( e.getStackTrace(),"未知错误",null,3);
        return R.error(401, "未知错误："+e.getMessage());
    }

    public void recodError(StackTraceElement[] stackTraceElements, String message, String methodParameter,Integer level){
        Log log=new Log();
        try {
            if (IdentityUtils.isAuthenticated()) {
                log.setCreateId(IdentityUtils.getUserId());
                log.setRemark("系统用户");
            } else {
                log.setRemark("未知用户");
            }
            log.setUrl(httpServletRequest.getRequestURI());
            log.setOperation(httpServletRequest.getMethod());
            log.setRemark(message);
            log.setIp(RequestUtils.getIp(httpServletRequest));
            log.setErrorStack(ExceptionUtils.getStackMsg(stackTraceElements));
            log.setLevel(level);
            log.setMethod(httpServletRequest.getMethod());
            log.setParams(JSON.toJSONString(httpServletRequest.getParameterMap()) + httpServletRequest.getQueryString());
            logDao.insert(log);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
