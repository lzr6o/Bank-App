package com.learning.Bank.exception;

public enum BankExceptionEnum {

	REGISTER_FAILED(100, "register failed"),
	LOGIN_FAILED(101, "login failed"),
	AUTHENTICATE_FAILED(102, "authenticate failed"),
	USER_NOT_FIND(103, "user not find"),
	ACCOUNT_CREATED_FAILED(104, "Account cannot be created"),
	ACCOUNT_NUMBER_WRONG(105, "Please check account number"),
	ACCOUNT_APPROVED_FAILED(106, "Account approved failed"),
	ACCOUNT_NOT_FOUND(107, "Account not found"),
	USER_UPDATE_FAILED(108, "User update failed"),
	USER_INFO_NOT_MATCH(109, "User information not match"),
	BENEFICIARY_ADD_FAILED(110, "Beneficiary add failed"),
	BENEFICIARY_NOT_FOUND(111, "Beneficiary not found"),
	BENEFICIARY_DELETE_FAILED(112, "Beneficiary delete failed"),
	ACCOUNT_BALANCE_INSUFFICIENT(113, "Account balance insufficient");

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
