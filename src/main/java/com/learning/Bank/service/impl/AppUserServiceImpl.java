package com.learning.Bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.Bank.entity.AppUser;
import com.learning.Bank.entity.Role;
import com.learning.Bank.exception.BankException;
import com.learning.Bank.repository.AppUserRepository;
import com.learning.Bank.repository.RoleRepository;
import com.learning.Bank.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public AppUser register(AppUser appUser) throws BankException {
		
		return appUserRepository.save(appUser);
	}

	@Override
	public Role saveRole(Role role) throws BankException {
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

	
}
