package com.learning.Bank.service;

import java.util.List;

import com.learning.Bank.entity.AppUser;
import com.learning.Bank.entity.Role;
import com.learning.Bank.exception.BankException;

public interface AppUserService {

	AppUser register(AppUser appUser);
	
	Role saveRole(Role role);
	
	void addRoleToUser(String username, String roleName);
	
	AppUser getUser(String username);
	
	List<AppUser> getUsers();

	AppUser authenticate(String username, String password);
	
}
