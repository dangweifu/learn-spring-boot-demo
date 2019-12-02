package com.xiaohei;

/**
 * @author : WiuLuS
 * @version : v1.11.29.2019
 * @discription :
 * @date : 2019-11-29 19:03:21
 * @email : m13886933623@163.com
 */
public class TopException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public TopException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TopException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public TopException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public TopException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
