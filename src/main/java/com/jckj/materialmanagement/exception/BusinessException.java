package com.jckj.materialmanagement.exception;

import com.jckj.materialmanagement.config.error.ErrorCodeProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class BusinessException {

    private String errorCode;

    private String errorMessage;

    private Object[] params;

    public BusinessException(String code, String msg) {
        this.errorCode = code;
        this.errorMessage = msg;
    }

    public BusinessException(String code) {
        this.errorCode = code;
        this.errorMessage = ErrorCodeProperties.init().getErrorMessage(code);
    }
    public BusinessException(String code, Object[] params) {
        this.errorCode = code;
        this.errorMessage = ErrorCodeProperties.init().getErrorMessages(code, params);
    }


}
