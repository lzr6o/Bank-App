package com.learning.Bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payload")
public class Payload {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "from_account_number")
	private long fromAccNumber;
	
	@Column(name = "to_account_number")
	private long toAccNumber;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "reason")
	private String reason;
	
	public Payload() {
		
	}

	public Payload(long fromAccNumber, long toAccNumber, double amount, String reason) {
		super();
		this.fromAccNumber = fromAccNumber;
		this.toAccNumber = toAccNumber;
		this.amount = amount;
		this.reason = reason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getFromAccNumber() {
		return fromAccNumber;
	}

	public void setFromAccNumber(long fromAccNumber) {
		this.fromAccNumber = fromAccNumber;
	}

	public long getToAccNumber() {
		return toAccNumber;
	}

	public void setToAccNumber(long toAccNumber) {
		this.toAccNumber = toAccNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Payload [id=" + id + ", fromAccNumber=" + fromAccNumber + ", toAccNumber=" + toAccNumber + ", amount="
				+ amount + ", reason=" + reason + "]";
	}
	
}