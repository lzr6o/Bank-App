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

	public Beneficiary() {
		
	}
	
	public Beneficiary(long beneficiaryAccountNumber, String beneficiaryName, Status accountStatus) {
		super();
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		this.beneficiaryName = beneficiaryName;
		this.accountStatus = accountStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(long beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public Status getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Status accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "Beneficiary [id=" + id + ", beneficiaryAccountNumber=" + beneficiaryAccountNumber + ", beneficiaryName="
				+ beneficiaryName + ", accountStatus=" + accountStatus + "]";
	}
	
}
