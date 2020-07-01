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

    private Integer code;

    private String message;

    private Object[] params;

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public BusinessException(Integer code) {
        this.code = code;
        this.message = ErrorCodeProperties.init().getErrorMessage(code);
    }
    public BusinessException(Integer code, Object[] params) {
        this.code = code;
        this.message = ErrorCodeProperties.init().getErrorMessages(code, params);
    }


}
