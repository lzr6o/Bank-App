package com.learning.Bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.Bank.common.ApiRestResponse;
import com.learning.Bank.entity.Account;
import com.learning.Bank.entity.AccountType;
import com.learning.Bank.entity.AppUser;
import com.learning.Bank.entity.Beneficiary;
import com.learning.Bank.entity.Payload;
import com.learning.Bank.exception.BankException;
import com.learning.Bank.exception.BankExceptionEnum;
import com.learning.Bank.service.AppUserService;

@RestController
public class AppUserController {

	@Autowired
	AppUserService appUserService;

	// customer registration
	// to register the user with basic details like
	@PostMapping("/customer/register")
	public ApiRestResponse registerCustomer(@RequestBody AppUser user) throws BankException {
		AppUser appUser = appUserService.registerCustomer(user);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.REGISTER_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}

	// customer authenticate
	// to validate the customer is registered in the system
	@PostMapping("/customer/authenticate")
	public ApiRestResponse authenticateCustomer(@RequestParam("username") String username,
			@RequestParam("password") String password) throws BankException {
		AppUser appUser = appUserService.authenticateCustomer(username, password);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.AUTHENTICATE_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}

	// customer create account
	// To create account for the customer
	@PostMapping("/customer/{customerID}/account")
	public ApiRestResponse createAccount(@PathVariable Integer customerID,
			@RequestParam("accountType") AccountType accountType, @RequestParam("accountBalance") double accountBalance,
			@RequestParam("approved") String approved) throws BankException {
		Account account = appUserService.createAccount(customerID, accountType, accountBalance, approved);
		if (account == null) {
			return ApiRestResponse.error(BankExceptionEnum.ACCOUNT_CREATED_FAILED);
		}
		return ApiRestResponse.success(account);
	}

	// Staff
	// to approve the account which is created by customer
	@PutMapping("/customer/{customerID}/account/{accountNo}")
	public ApiRestResponse approveAccount(@PathVariable Integer customerID, @PathVariable Integer accountNo,
			@RequestParam("approved") String approved) throws BankException {
		Account account = appUserService.approveAccount(customerID, accountNo, approved);
		if (account == null) {
			return ApiRestResponse.error(BankExceptionEnum.ACCOUNT_APPROVED_FAILED);
		}
		return ApiRestResponse.success(account);
	}

	// to get all the accounts which are opened by the customer the end point should
	// return an array of account, balance, type and status
	@GetMapping("customer/{customerID}/account")
	public ApiRestResponse getAllAccounts(@PathVariable Integer customerID) throws BankException {
		List<Account> accounts = appUserService.getAllAccounts(customerID);
		if (accounts == null) {
			return ApiRestResponse.error(BankExceptionEnum.ACCOUNT_NOT_FOUND);
		}
		return ApiRestResponse.success(accounts);
	}

	// to return customer by specifying id
	@GetMapping("customer/{customerID}")
	public ApiRestResponse getCustomer(@PathVariable Integer customerID) throws BankException {
		AppUser appUser = appUserService.getCustomer(customerID);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.USER_NOT_FIND);
		}
		return ApiRestResponse.success(appUser);
	}

	// should update the user customer in the payload which shall match the username
	// and updated the existing customer with the new details
	@PutMapping("customer/{customerID}")
	public ApiRestResponse updateCustomer(@PathVariable Integer customerID, @RequestBody AppUser user)
			throws BankException {
		AppUser appUser = appUserService.updateCustomer(customerID, user);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.USER_UPDATE_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}

	// should get the customer account with the specified account number :accountID
	// when valid
	@GetMapping("customer/{customerID}/account/{accountID}")
	public ApiRestResponse getCustomerAccount(@PathVariable Integer customerID, @PathVariable Integer accountID)
			throws BankException {
		Account account = appUserService.getCustomerAccount(customerID, accountID);
		if (account == null) {
			return ApiRestResponse.error(BankExceptionEnum.ACCOUNT_NOT_FOUND);
		}
		return ApiRestResponse.success(account);
	}

	// Should add the beneficiary for the customer with valid account number
	@PostMapping("customer/{customerID}/beneficiary")
	public ApiRestResponse addCustomerBeneficiary(@PathVariable Integer customerID,
			@RequestParam("accountNumber") long accountNumber, @RequestBody Beneficiary beneficiary)
			throws BankException {
		AppUser appUser = appUserService.addCustomerBeneficiary(customerID, accountNumber, beneficiary);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.BENEFICIARY_ADD_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}

	// Should get all the beneficiary for the given customer id
	@GetMapping("customer/{customerID}/beneficiary")
	public ApiRestResponse addBeneficiary(@PathVariable Integer customerID) throws BankException {
		List<Beneficiary> beneficiarys = appUserService.getCustomerBeneficiary(customerID);
		return ApiRestResponse.success(beneficiarys);
	}

	// Should delete customer beneficiary
	@DeleteMapping("customer/{customerID}/beneficiary/{beneficiaryID}")
	public ApiRestResponse deleteBeneficiary(@PathVariable Integer customerID, @PathVariable Integer beneficiaryID)
			throws BankException {
		Beneficiary beneficiary = appUserService.deleteCustomerBeneficiary(customerID, beneficiaryID);
		if (beneficiary == null) {
			return ApiRestResponse.error(BankExceptionEnum.BENEFICIARY_DELETE_FAILED);
		}
		return ApiRestResponse.success(beneficiary);
	}

	// Should transfer the amount from one customer account to another account
	@PutMapping("customer/transfer")
	public ApiRestResponse transfer(@RequestParam("customerID") Integer customerID, @RequestBody Payload payload) {
		List<Account> accounts = appUserService.transfer(customerID, payload);
		return ApiRestResponse.success(accounts);
	}

	// Customer forget security question answer
	@GetMapping("customer/{username}/forgot/question/answer")
	public ApiRestResponse validSecurityQuestion(@PathVariable String username, @RequestParam("answer") String answer) {
		AppUser appUser = appUserService.validSecurityQuestion(username, answer);
		return ApiRestResponse.success(appUser);
	}

	@PutMapping("customer/{username}/forgot")
	public ApiRestResponse resetPassword(@PathVariable String username, @RequestParam("password") String password) {
		AppUser appUser = appUserService.resetPassword(username, password);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.PASSWORD_UPDATE_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}

	// To validate the staff should be able to login to the system with validate username and password
	@PostMapping("/staff/authenticate")
	public ApiRestResponse authenticateStaff(@RequestParam("username") String username,
			@RequestParam("password") String password) throws BankException {
		AppUser appUser = appUserService.authenticateStaff(username, password);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.AUTHENTICATE_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}
	
	// Role: Staff
	// To get the statement of particular account, given the account id
	//@GetMapping("/staff/account/{accountNo}")
	//public ApiRestResponse 
}
