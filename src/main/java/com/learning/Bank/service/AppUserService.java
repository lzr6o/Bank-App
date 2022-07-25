package com.learning.Bank.service;

import java.util.List;

import com.learning.Bank.entity.Account;
import com.learning.Bank.entity.AccountType;
import com.learning.Bank.entity.AppUser;
import com.learning.Bank.entity.Beneficiary;

public interface AppUserService {

	AppUser register(AppUser appUser);

	AppUser authenticate(String username, String password);

	Account createAccount(Integer customerID, AccountType accountType, double accountBalance, String approved);

	Account approveAccount(Integer customerID, Integer accountNumber, String approved);

	List<Account> getAllAccounts(Integer customerID);

	AppUser getCustomer(Integer customerID);

	AppUser updateCustomer(Integer customerID, AppUser appUser);

	Account getCustomerAccount(Integer customerID, Integer accountID);

	AppUser addCustomerBeneficiary(Integer customerID, long accountNumber, Beneficiary beneficiary);
	
	List<Beneficiary> getCustomerBeneficiary(Integer customerID);

	Beneficiary deleteCustomerBeneficiary(Integer customerID, Integer beneficiaryID);
	
	
}
