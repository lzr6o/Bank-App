package com.learning.Bank.service;

import java.util.List;

import com.learning.Bank.entity.AppUser;
import com.learning.Bank.entity.Role;
import com.learning.Bank.exception.BankException;

public interface AppUserService {

	AppUser register(AppUser appUser) throws BankException;
	
	Role saveRole(Role role) throws BankException;
	
	void addRoleToUser(String username, String roleName);
	
	AppUser getUser(String username);
	
	List<AppUser> getUsers();
	
}
