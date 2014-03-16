package com.brandtone.bank.domain;

import java.util.Date;

public class Transaction {
	
	private Long id;
	private String transactionType;
	private Account fromAccount;
	private Account toAccount;
	private double amount;
	private Date transactionDate;
	
	public Transaction(Long id,
			String transactionType,
			Account fromAccount,
			Account toAccount,
			double amount,
			Date transactionDate) {
		
		this.transactionType = transactionType;
		this.amount = amount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionDate = new Date();
	}
	
	public Long getId() { return id; }
	public Date getTransactionDate() { return transactionDate; }
	public String getTransactionType() { return transactionType; }
	public double getAmount() { return amount; }
	public Account getAccountFrom() { return fromAccount; }
	public Account getAccountTo() {return toAccount; }
}
