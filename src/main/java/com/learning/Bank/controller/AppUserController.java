package com.learning.Bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.Bank.common.ApiRestResponse;
import com.learning.Bank.entity.Account;
import com.learning.Bank.entity.AccountType;
import com.learning.Bank.entity.AppUser;
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
	@ResponseBody
	public ApiRestResponse register(@RequestBody AppUser user) throws BankException {
		AppUser appUser = appUserService.register(user);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.REGISTER_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}

	// customer authenticate
	// to validate the customer is registered in the system
	@PostMapping("/customer/authenticate")
	@ResponseBody
	public ApiRestResponse authenticate(@RequestParam("username") String username, @RequestParam("password") String password) throws BankException {
		AppUser appUser = appUserService.authenticate(username, password);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.AUTHENTICATE_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}

	// customer create account
	// To create account for the customer
	@PostMapping("/customer/{customerID}/account")
	@ResponseBody
	public ApiRestResponse createAccount(@PathVariable Integer customerID, @RequestParam("accountType") AccountType accountType, @RequestParam("accountBalance") double accountBalance, @RequestParam("approved") String approved) throws BankException {
		Account account = appUserService.createAccount(customerID, accountType, accountBalance, approved);
		if (account == null) {
			return ApiRestResponse.error(BankExceptionEnum.ACCOUNT_CREATED_FAILED);
		}
		return ApiRestResponse.success(account);
	}
	
	// Staff
	// to approve the account which is created by customer
	@PutMapping("/customer/{customerID}/account/{accountNo}")
	@ResponseBody
	public ApiRestResponse approveAccount(@PathVariable Integer customerID, @PathVariable Integer accountNo, @RequestParam("approved") String approved) throws BankException {
		Account account = appUserService.approveAccount(customerID, accountNo, approved);
		if (account == null) {
			return ApiRestResponse.error(BankExceptionEnum.ACCOUNT_APPROVED_FAILED);
		}
		return ApiRestResponse.success(account);
	}

	// to get all the accounts which are opened by the customer
	// the end point should return an array of account, balance, type and status
	@GetMapping("customer/{customerID}/account")
	@ResponseBody
	public ApiRestResponse getAllAccounts(@PathVariable Integer customerID) throws BankException {
		List<Account> accounts = appUserService.getAllAccounts(customerID);
		if (accounts == null) {
			return ApiRestResponse.error(BankExceptionEnum.ACCOUNT_NOT_FOUND);
		}
		return ApiRestResponse.success(accounts);
	}
	
	// to return customer by specifying id
	@GetMapping("customer/{customerID}")
	@ResponseBody
	public ApiRestResponse getCustomer(@PathVariable Integer customerID) throws BankException {
		AppUser appUser = appUserService.getCustomer(customerID);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.USER_NOT_FIND);
		}
		return ApiRestResponse.success(appUser);
	}
	
	// Should update the user customer in the payload which shall match the username and updated the existing customer with the new details
	@PutMapping("customer/{customerID}")
	@ResponseBody
	public ApiRestResponse updateCustomer(@PathVariable Integer customerID, @RequestBody AppUser user) throws BankException {
		AppUser appUser = appUserService.updateCustomer(customerID, user);
		if (appUser == null) {
			return ApiRestResponse.error(BankExceptionEnum.USER_UPDATE_FAILED);
		}
		return ApiRestResponse.success(appUser);
	}
}
