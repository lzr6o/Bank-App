package com.learning.Bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "account")
public class Account {

	enum accountType {
		SB, CA
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "account_number")
	private long accountNumber;
	
	@Column(name = "account_balance")
	private double accountBalance;
	
	@Column(name = "approved")
	private String approved = "NO";
	
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_creation")
	private Date dateOfCreation;
	
	

}
