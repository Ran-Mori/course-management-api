package com.constant;

public enum ErrorCode {

    REQUEST_NULL("T001","请求域为空或不存在"),
    INSERT_ERROR("T002","数据库inset新增错误"),
    SELECT_ERROR("t003","数据库查询错误"),
    PARSE_REQUEST_ERROR("T004","解析请求域参数失败"),

    ACCOUNT_ILL("U001","用户名必须是13位整数"),
    PASSWORD_ILL("U002","密码过于简单"),
    ACCOUNT_EXIST("U003","此用户名已经存在"),
    ACCOUNT_NOT_EXIST("U004","此用户不存在"),
    PASSWORD_ERROR("U005","密码错误");

    private String code;
    private String message;

    ErrorCode(String code,String message){
        this.code=code;
        this.message=message;
    }



    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
