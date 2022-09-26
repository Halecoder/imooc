
package com.imooc.reader.service.exception;

/**
 * 自定义异常类；与业务逻辑相关的异常
 */
public class BussinessException extends RuntimeException{
    private String code;
    private String msg;

    public BussinessException(String code, String msg) {
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
  