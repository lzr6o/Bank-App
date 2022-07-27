package com.learning.Bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.Bank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByAccountNumber(long accountNumber);
	
	
}
