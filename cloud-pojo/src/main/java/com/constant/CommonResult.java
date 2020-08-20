package com.constant;

import java.util.Map;

public class CommonResult {
    private String code;
    private String message;
    private Map<String,Object> data;

    public static CommonResult success(){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode("200");
        commonResult.setMessage("成功");
        return commonResult;
    }

    public CommonResult add(String key,Object value){
        this.getData().put(key, value);
        return this;
    }

    public static CommonResult fail(ErrorCode errorCode){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(errorCode.getCode());
        commonResult.setMessage(errorCode.getMessage());
        return commonResult;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
