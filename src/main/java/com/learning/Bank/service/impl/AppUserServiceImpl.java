package com.learning.Bank.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
	    int accountNumber = generateRandomAccountNumber(8);
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAccountType(accountType);
		account.setAccountBalance(accountBalance);
		account.setApproved(approved);
		appUser.getAccounts().add(account);
		accountRepository.save(account);
		return account;
	}

	// Generates a random int with n digits
	public int generateRandomAccountNumber(int n) {
	    int m = (int) Math.pow(10, n - 1);
	    return m + new Random().nextInt(9 * m);
	}
	
}
