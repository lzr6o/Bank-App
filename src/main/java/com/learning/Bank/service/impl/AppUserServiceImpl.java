package com.learning.Bank.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.Bank.entity.Account;
import com.learning.Bank.entity.AccountType;
import com.learning.Bank.entity.AppUser;
import com.learning.Bank.entity.Role;
import com.learning.Bank.exception.BankException;
import com.learning.Bank.exception.BankExceptionEnum;
import com.learning.Bank.repository.AccountRepository;
import com.learning.Bank.repository.AppUserRepository;
import com.learning.Bank.repository.RoleRepository;
import com.learning.Bank.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public AppUser register(AppUser appUser) {
		return appUserRepository.save(appUser);
	}

	@Override
	public AppUser authenticate(String username, String password) {
		return appUserRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser appUser = appUserRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		appUser.getRoles().add(role);
		appUserRepository.save(appUser);
	}

	@Override
	public AppUser getUser(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> getUsers() {
		return appUserRepository.findAll();
	}
	
	@Override
	public Account createAccount(Integer customerID, AccountType accountType, double accountBalance, String approved) {
		Optional<AppUser> appUser = appUserRepository.findById(customerID);
		if (!appUser.isPresent()) {
			throw new BankException(BankExceptionEnum.USER_NOT_FIND);
		}
		
		Account account = new Account(accountType, accountBalance, approved);
		
		accountRepository.save(account);
		return account;
	}

}
