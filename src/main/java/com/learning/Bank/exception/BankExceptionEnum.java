package com.learning.Bank.exception;

public enum BankExceptionEnum {
	
	REGISTER_FAILED(100, "register failed");
	
	Integer code;
	String msg;
	BankExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
