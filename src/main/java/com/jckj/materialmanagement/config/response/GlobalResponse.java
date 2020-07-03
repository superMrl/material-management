package com.jckj.materialmanagement.config.response;


import com.jckj.materialmanagement.config.error.ErrorCode;
import com.jckj.materialmanagement.config.error.ErrorCodeProperties;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * http/https通用返回值
 * 参考文档 https://zhuanlan.zhihu.com/p/139027522
 */
@Data
@ToString
@NoArgsConstructor
public class GlobalResponse<T> {

    protected boolean success = false;
    private T data;
    private String code;
    private String msg;

    public GlobalResponse(T data, String code, String msg, boolean success) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> GlobalResponse<T> success() {
        return new GlobalResponse<>(null, ErrorCode.SUCCESS, ErrorCodeProperties.init().getErrorMessage(ErrorCode.SUCCESS), true);
    }

    public static <T> GlobalResponse<T> success(String code) {
        return new GlobalResponse<>(null, code, ErrorCodeProperties.init().getErrorMessage(code), true);
    }

    public static <T> GlobalResponse<T> success(T data) {
        return new GlobalResponse<>(data, ErrorCode.SUCCESS, ErrorCodeProperties.init().getErrorMessage(ErrorCode.SUCCESS), true);
    }

    public static <T> GlobalResponse<T> success(String code,T data) {
        return new GlobalResponse<>(data, code, ErrorCodeProperties.init().getErrorMessage(code), true);
    }

    public static <T> GlobalResponse<T> fail(String code,String msg) {
        GlobalResponse<T> resp = new GlobalResponse<T>();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setSuccess(false);
        return resp;
    }

    public static <T> GlobalResponse<T> fail(String msg) {
        GlobalResponse<T> resp = new GlobalResponse<T>();
        resp.setCode(ErrorCode.FAILED);
        resp.setMsg(ErrorCodeProperties.init().getErrorMessage(msg));
        resp.setSuccess(false);
        return resp;
    }
}
