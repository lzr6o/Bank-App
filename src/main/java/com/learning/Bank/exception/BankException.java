package com.learning.Bank.exception;

public class BankException extends RuntimeException{
	
	private final Integer code;
    private final String message;

    public BankException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BankException(BankExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
