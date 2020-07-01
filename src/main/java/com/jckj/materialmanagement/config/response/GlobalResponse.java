package com.jckj.materialmanagement.config.response;


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

    public GlobalResponse(T data, boolean success) {
        this.success = success;
        this.data = data;
    }

    public static <T> GlobalResponse<T> success(T data) {
        return new GlobalResponse<>(data, true);
    }

    public static <T> GlobalResponse<T> fail(String msg, String code) {
        GlobalResponse<T> resp = new GlobalResponse<T>();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setSuccess(false);
        return resp;
    }
}
