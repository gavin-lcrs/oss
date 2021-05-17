package com.gavin.oss.common;

import com.gavin.oss.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author jiwen.cao
 * @Date 2021/5/17
 * @Description
 */
@Data
public class ResultMessage<T> implements Serializable {

    private String code;
    private T data;
    private String message;

    public ResultMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultMessage(String code,T data){
        this.code = code;
        this.data = data;
    }
    private ResultMessage(String code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> ResultMessage<T> success(){
        return new ResultMessage<>(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message);
    }
    public static <T> ResultMessage<T> success(T data){
        return new ResultMessage<T>(ResultCode.SUCCESS.code, data);
    }

    public static <T> ResultMessage<T> fail(String message){
        return new ResultMessage<>(ResultCode.FAIL.code, message);
    }
    public static <T> ResultMessage<T> fail(ResultCode code){
        return new ResultMessage<>(code.code, code.message);
    }
}
