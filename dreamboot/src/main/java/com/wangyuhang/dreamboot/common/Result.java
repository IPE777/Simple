package com.wangyuhang.dreamboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String message;
    private String code;
    private Boolean success;
    private T result;
    private Long timeStamp = System.currentTimeMillis();

    public static Result ok(Object obj){
        Result<Object> result = new Result<>();
        result.setCode("200");
        result.setMessage("操作成功");
        result.setSuccess(true);
        result.setResult(obj);
        return result;
    }

    public static Result error(String message){
        Result<Object> result = new Result<>();
        result.setCode("400");
        result.setSuccess(false);
        result.setResult(null);
        result.setMessage(message);
        return result;
    }
}
