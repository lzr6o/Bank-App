package com.learning.Bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.Bank.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer>{
	
	AppUser findByUsername(String username);

	AppUser findByUsernameAndPassword(String username, String password);
	
}
