package com.learning.Bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beneficiary")
public class Beneficiary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "beneficiary_account_number")
	private long beneficiaryAccountNumber;
	
	@Column(name = "beneficiary_name")
	private String beneficiaryName;
	
	@Column(name = "account_status")
	@Enumerated(EnumType.STRING)
	private Status accountStatus = Status.ENABLE;
	
	
}
