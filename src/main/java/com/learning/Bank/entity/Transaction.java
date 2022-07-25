package com.learning.Bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "reference")
	private String reference;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "card_type")
	@Enumerated(EnumType.STRING)
	private CardType cardType;

	public Transaction() {
		
	}
	
	public Transaction(Date date, String reference, Double amount, CardType cardType) {
		super();
		this.date = date;
		this.reference = reference;
		this.amount = amount;
		this.cardType = cardType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", reference=" + reference + ", amount=" + amount
				+ ", cardType=" + cardType + "]";
	}
	
}
