package com.brandtone.bank.fixtures;

import com.brandtone.bank.domain.Account;

public class AccountFixtures {
	
	public static Account typicalAccount() {
		return new Account(1,1111, "name", "address", "phone", 20.00);
	}

	public static Account typicalAccountTwo() {
		return new Account(2,2222, "name", "address", "phone", 40.00);
	}
	
	public static Account badAccountName() {
		return new Account(3, 3333, "", "address", "phone",60.00);
	}
	
	public static Account badAccountBalance() {
		return new Account(4, 4444, "name", "address", "phone",new Double("invalid"));
	}
	
	public static Account badAccountAddress() {
		return new Account(5,5555,"name", null, "phone",80.00);
	}
	
	public static Account typicalAccountClone() {
		return AccountFixtures.typicalAccount();
	}
}
