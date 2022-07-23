package com.learning.Bank.service;

import java.util.List;

import com.learning.Bank.entity.Account;
import com.learning.Bank.entity.AccountType;
import com.learning.Bank.entity.AppUser;
import com.learning.Bank.entity.Role;

public interface AppUserService {

	AppUser register(AppUser appUser);
	
	Role saveRole(Role role);
	
	void addRoleToUser(String username, String roleName);
	
	AppUser getUser(String username);
	
	List<AppUser> getUsers();

	AppUser authenticate(String username, String password);

	Account createAccount(Integer customerID, AccountType accountType, double accountBalance, String approved);

	Account approveAccount(Integer customerID, Integer accountNumber, String approved);
	
}
