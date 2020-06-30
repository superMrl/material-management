package com.jckj.materialmanagement.exception;

import com.jckj.materialmanagement.config.error.ErrorCodeProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class BusinessException {

    private String code;

    private String message;

    private Object[] params;

    public BusinessException(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public BusinessException(String code) {
        this.code = code;
        this.message = ErrorCodeProperties.init().getErrorMessage(code);
    }
    public BusinessException(String code, Object[] params) {
        this.code = code;
        this.message = ErrorCodeProperties.init().getErrorMessages(code, params);
    }


}
