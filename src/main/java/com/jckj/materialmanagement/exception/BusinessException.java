package com.jckj.materialmanagement.exception;

import com.jckj.materialmanagement.config.error.ErrorCodeProperties;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * 业务异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException{

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
