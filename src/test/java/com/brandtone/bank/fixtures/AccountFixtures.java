package com.brandtone.bank.fixtures;

import java.util.ArrayList;
import java.util.List;

import com.brandtone.bank.domain.Account;

public class AccountFixtures {
	
	public static Account typicalAccount() {
		return Account.newInstance(11111, "name", "address", "phone", 20.00);
	}

	public static Account typicalAccountTwo() {
		return Account.newInstance(22222, "name", "otheraddress", "otherphone", 20.00);
	}
	
	public static Account badAccountName() {
		return Account.newInstance(33333, "", "address", "phone",60.00);
	}
	
	public static Account badAccountBalance() {
		return Account.newInstance(44444, "name", "address", "phone",new Double("invalid"));
	}
	
	public static Account badAccountAddress() {
		return Account.newInstance(5555,"name", null, "phone",80.00);
	}
	
	public static Account typicalAccountClone() {
		return AccountFixtures.typicalAccount();
	}
	
	public static List<Account> typicalAccounts() {
		
		Account accountOne = Account.newInstance(88, "name", "address", "phone", 20.00);
		Account accountTwo = Account.newInstance(89, "name", "address", "phone", 40.00);
		
		List<Account> typicalAccounts = new ArrayList<>();
		typicalAccounts.add(accountOne);
		typicalAccounts.add(accountTwo);
		
		return typicalAccounts;		
	}
}
