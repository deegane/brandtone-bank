package com.brandtone.bank.fixtures;

import com.brandtone.bank.domain.Account;

public class AccountFixtures {
	
	public static Account typicalAccount() {
		return new Account(1,1111, "name", "address", "phone", 20.00);
	}

}
