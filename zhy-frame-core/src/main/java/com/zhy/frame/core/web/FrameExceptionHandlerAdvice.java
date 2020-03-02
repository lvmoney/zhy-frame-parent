package com.zhy.frame.core.web;

import com.zhy.frame.core.api.ApiResult;
import com.zhy.frame.core.exception.BusinessException;
import com.zhy.frame.core.exception.CommonException;
import com.zhy.frame.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月15日 上午9:58:40
 */
@RestControllerAdvice
public class FrameExceptionHandlerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrameExceptionHandlerAdvice.class);
    /**
     * 开头即为404
     */
    private static final String ERROR_404_START = "No handler found for";

    /**
     * 未解决的错误，需要配合yml配置来使用
     * spring.mvc.throw-exception-if-no-handler-found=true
     * 1、如果不引用shiro，系统会在FrameErrorController中处理错误信息
     * 2、引用了shiro后HttpServletRequest会强制转化成ShiroHttpServletRequest，1中的方法失效
     * 3、2的使用需要结合注解
     * 4、由于shiro的特殊性，需要定制返回值
     *
     * @param request:
     * @param e:
     * @throws
     * @return: com.zhy.frame.core.api.ApiResult<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/2 15:54
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public void handle(HttpServletRequest request, HttpServletResponse res, NoHandlerFoundException e) {
        String msg = e.getLocalizedMessage();
        Integer status = getCodeByErrorMsg(msg);
        res.setStatus(status);
        responsePrint(res, status, msg);
    }

    /**
     * 实在找不到错误了，那就进入这个错误
     *
     * @param req:
     * @param res:
     * @param e:
     * @throws
     * @return: com.zhy.frame.core.api.ApiResult<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/2 15:54
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleException(HttpServletRequest req, HttpServletResponse res, Exception e) {
        LOGGER.error("系统错误", e);
        return ApiResult.error(CommonException.Proxy.OTHER_ERROR.getCode(), CommonException.Proxy.OTHER_ERROR.getDescription());
    }

    /**
     * 业务请求的错误都到这里
     *
     * @param req:
     * @param res:
     * @param e:
     * @throws
     * @return: com.zhy.frame.core.api.ApiResult<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/2 15:55
     */
    @ExceptionHandler(BusinessException.class)
    public ApiResult<?> handleBusinessException(HttpServletRequest req, HttpServletResponse res, BusinessException e) {
        LOGGER.error("系统错误", e);
        return ApiResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 参数绑定的错误
     *
     * @param req:
     * @param res:
     * @param e:
     * @throws
     * @return: com.zhy.frame.core.api.ApiResult<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/2 15:55
     */
    @ExceptionHandler(BindException.class)
    public ApiResult<?> handleBindException(HttpServletRequest req, HttpServletResponse res, BindException e) {
        BindException bException = (BindException) e;
        List<BeanValidateExceptionVo> excepitonVos = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : bException.getBindingResult().getFieldErrors()) {
            BeanValidateExceptionVo excepitonVo = new BeanValidateExceptionVo();
            excepitonVo.setDefaultMessage(error.getDefaultMessage());
            excepitonVo.setField(error.getField());
            excepitonVo.setRejectedValue(error.getRejectedValue());
            excepitonVos.add(excepitonVo);
        }
        return ApiResult.error(CommonException.Proxy.PARAM_ERROR.getCode(), CommonException.Proxy.PARAM_ERROR.getDescription(), excepitonVos);
    }

    private void responsePrint(ServletResponse response, Integer status, String message) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(status);
        apiResult.setSuccess(false);
        apiResult.setMsg(message);
        apiResult.setDate(new Date());
        String json = JsonUtil.t2JsonString(apiResult);
        try {
            httpResponse.getWriter().print(json);
        } catch (IOException e) {
            LOGGER.error("其他错误处理response返回处理报错：{}", e.getMessage());
        }
    }

    /**
     * 由于shiro的特性，从这里来判断错误码
     * 1、通过错误信息start的字符
     * 2、目前只知道404，其他的遇到再加
     * 3、这种情况使用极少，是在故意报错的情况会用到
     *
     * @param msg:
     * @throws
     * @return: java.lang.Integer
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/2 16:45
     */
    private Integer getCodeByErrorMsg(String msg) {
        if (msg.startsWith(ERROR_404_START)) {
            return HttpStatus.NOT_FOUND.value();
        } else {
            return CommonException.Proxy.OTHER_ERROR.getCode();
        }
    }


}
