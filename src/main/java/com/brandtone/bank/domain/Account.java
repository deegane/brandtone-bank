package com.brandtone.bank.domain;

public class Account {

	private Long id;
	private long number;
	private final String name;
	private final String address;
	private final String phone;
	private double balance;
		
	public Account(long number, 
			String name, 
			String address, 
			String phone, 
			double balance) {
		this.number = number;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.balance = balance;
	}
	
	public Long getId() { return id; }
	public long getAccountNumber() { return number; }
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getPhone() { return phone; }
	public double getBalance() { return balance; }
}
