package com.learning.Bank.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.Bank.entity.Account;
import com.learning.Bank.entity.AccountType;
import com.learning.Bank.entity.AppUser;
import com.learning.Bank.entity.Beneficiary;
import com.learning.Bank.entity.Payload;
import com.learning.Bank.entity.Role;
import com.learning.Bank.exception.BankException;
import com.learning.Bank.exception.BankExceptionEnum;
import com.learning.Bank.repository.AccountRepository;
import com.learning.Bank.repository.AppUserRepository;
import com.learning.Bank.repository.BeneficiaryRepository;
import com.learning.Bank.repository.PayloadRepository;
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

	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	PayloadRepository payloadRepository;
	
	@Override
	public AppUser register(AppUser appUser) {
		Role role = roleRepository.findByName("CUSTOMER");
		appUser.addRole(role);
		return appUserRepository.save(appUser);
	}

	@Override
	public AppUser authenticate(String username, String password) {
		return appUserRepository.findByUsernameAndPassword(username, password);
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
		appUserRepository.save(appUser);
		return account;
	}

	// Generates a random int with n digits
	public int generateRandomAccountNumber(int n) {
		int m = (int) Math.pow(10, n - 1);
		return m + new Random().nextInt(9 * m);
	}

	@Override
	public Account approveAccount(Integer customerID, Integer accountNumber, String approved) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		List<Account> accounts = appUser.getAccounts();
		Account currentAccount = null;
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				currentAccount = account;
				break;
			}
		}
		if (currentAccount == null) {
			throw new BankException(BankExceptionEnum.ACCOUNT_NUMBER_WRONG);
		}
		currentAccount.setApproved(approved);
		accountRepository.save(currentAccount);
		return currentAccount;
	}

	@Override
	public List<Account> getAllAccounts(Integer customerID) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		List<Account> accounts = appUser.getAccounts();
		return accounts;
	}

	@Override
	public AppUser getCustomer(Integer customerID) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		return appUser;
	}

	@Override
	public AppUser updateCustomer(Integer customerID, AppUser user) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		if (appUser.getUsername() != user.getUsername()) {
			throw new BankException(BankExceptionEnum.USER_INFO_NOT_MATCH);
		}
		appUser.setUsername(user.getUsername());
		appUser.setPassword(user.getPassword());
		appUser.setFullname(user.getFullname());
		appUser.setPhone(user.getPhone());
		appUser.setPan(user.getPan());
		appUser.setAadhar(user.getAadhar());
		appUser.setSecretQuestion(user.getSecretQuestion());
		appUser.setSecretAnswer(user.getSecretAnswer());
		appUserRepository.save(appUser);
		return appUser;
	}
	
	@Override
	public Account getCustomerAccount(Integer customerID, Integer accountID) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		List<Account> accounts = appUser.getAccounts();
		Account currentAccount = null;
		for (Account account : accounts) {
			if (account.getId() == accountID) {
				currentAccount = account;
				break;
			}
		}
		if (currentAccount == null) {
			throw new BankException(BankExceptionEnum.ACCOUNT_NOT_FOUND);
		}
		return currentAccount;
	}
	
	@Override
	public AppUser addCustomerBeneficiary(Integer customerID, long accountNumber, Beneficiary beneficiary) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		List<Account> accounts = appUser.getAccounts();
		Account currentAccount = null;
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				currentAccount = account;
				break;
			}
		}
		if (currentAccount == null) {
			throw new BankException(BankExceptionEnum.ACCOUNT_NOT_FOUND);
		}
		appUser.addBeneficiary(beneficiary);
		beneficiaryRepository.save(beneficiary);
		return appUser;
	}
	
	@Override
	public List<Beneficiary> getCustomerBeneficiary(Integer customerID) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		List<Beneficiary> beneficiarys = appUser.getBeneficiarys();
		return beneficiarys;
	}
	
	@Override
	public Beneficiary deleteCustomerBeneficiary(Integer customerID, Integer beneficiaryID) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		List<Beneficiary> beneficiarys = appUser.getBeneficiarys();
		Beneficiary currentBeneficiary = null;
		for (Beneficiary beneficiary : beneficiarys) {
			if (beneficiary.getId() == beneficiaryID) {
				currentBeneficiary = beneficiary;
				break;
			}
		}
		if (currentBeneficiary == null) {
			throw new BankException(BankExceptionEnum.BENEFICIARY_NOT_FOUND);
		}
		appUser.getBeneficiarys().remove(currentBeneficiary);
		appUserRepository.save(appUser);
		beneficiaryRepository.delete(currentBeneficiary);
		return currentBeneficiary;
	}
	
	@Override
	public List<Account> transfer(Integer customerID, Payload payload) {
		Optional<AppUser> optionalAppUser = appUserRepository.findById(customerID);
		AppUser appUser = optionalAppUser.orElseThrow(() -> new BankException(BankExceptionEnum.USER_NOT_FIND));
		List<Account> accounts = appUser.getAccounts();
		Account fromAcc = null, toAcc = null;
		for (Account account : accounts) {
			if (account.getAccountNumber() == payload.getFromAccNumber()) {
				fromAcc = account;
			}
			if (account.getAccountNumber() == payload.getToAccNumber()) {
				toAcc = account;
			}
			if (fromAcc != null && toAcc != null) {
				break;
			}
		}
		if (fromAcc == null || toAcc == null) {
			throw new BankException(BankExceptionEnum.ACCOUNT_NUMBER_WRONG);
		}
		if (fromAcc.getAccountBalance() < payload.getAmount()) {
			throw new BankException(BankExceptionEnum.ACCOUNT_BALANCE_INSUFFICIENT);
		}
		fromAcc.setAccountBalance(fromAcc.getAccountBalance() - payload.getAmount());
		toAcc.setAccountBalance(toAcc.getAccountBalance() + payload.getAmount());
		accountRepository.save(fromAcc);
		accountRepository.save(toAcc);
		appUser.addPayload(payload);
		appUserRepository.save(appUser);
		return accounts;
	}
	
	@Override
	public AppUser resetPassword(String username, String password) {
		AppUser appUser = appUserRepository.findByUsername(username);
		if (appUser == null) {
			throw new BankException(BankExceptionEnum.USER_NOT_FIND);
		}
		appUser.setPassword(password);
		appUserRepository.save(appUser);
		return appUser;
	}
}
