package com.gavin.oss.enums;

/**
 * @Author jiwen.cao
 * @Date 2021/5/17
 * @Description
 */
public enum ResultCode {
    /** 成功 */
    SUCCESS("200","操作成功"),
    /** 系统异常 */
    FAIL("500","系统异常"),
    /** 节点或前缀错误 */
    POINT_OR_PREFIX_ERROR("404","节点或前缀错误"),
    /** 节点错误 */
    POINT_ERROR("404","节点错误"),
    /** 前缀错误 */
    PREFIX_ERROR("404","前缀错误"),
    DIRECTORY_NULL("404","前缀不能为空"),
    ;

    public String  code;
    public String  message;

    ResultCode(String code,String message){
        this.code = code;
        this.message = message;
    }
}
