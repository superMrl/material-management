package com.jckj.materialmanagement.config.response;

import com.google.common.base.Throwables;
import com.jckj.materialmanagement.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一处理handler
 */
@Slf4j
//controller增强处理器
@ControllerAdvice(annotations = {RestController.class, Controller.class})

public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 对哪些进行拦截处理
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        final String returnTypeName = returnType.getParameterType().getName();
        return !"com.jckj.materialmanagement.config.response.GlobalResponse".equals(returnTypeName)
                && !"org.springframework.http.ResponseEntity".equals(returnTypeName);
    }

    /**
     * 进行统一数据处理
     *
     * @param body
     * @param returnType
     * @param selectContentType
     * @param selectConverterType
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectContentType, Class<? extends HttpMessageConverter<?>> selectConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        final String returnTypeName = returnType.getParameterType().getName();
        if ("void".equals(returnTypeName)) {
            return GlobalResponse.success(null);
        }
        if (!selectContentType.includes(MediaType.APPLICATION_JSON)) {
            return body;
        }
        if ("com.jckj.materialmanagement.config.response.GlobalResponse".equals(returnTypeName)) {
            return body;
        }
        return GlobalResponse.success(body);
    }

    //统一异常处理
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BusinessException.class})
    public <T> GlobalResponse<T> handleException(BusinessException e){
        log.error(Throwables.getStackTraceAsString(e));
        return GlobalResponse.fail(e.getCode(),e.getMessage());
    }

    //统一异常处理
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Throwable.class})
    public <T> GlobalResponse<T> handleThrowable(Throwable e){
        log.error(Throwables.getStackTraceAsString(e));
        return GlobalResponse.fail(Throwables.getStackTraceAsString(e),null);
    }
}
