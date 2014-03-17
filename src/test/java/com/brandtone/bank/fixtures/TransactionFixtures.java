package com.brandtone.bank.fixtures;

import com.brandtone.bank.domain.Transaction;
import com.brandtone.bank.fixtures.AccountFixtures;


public class TransactionFixtures {
	
	
	public static Transaction typicalTransfer() {
		return Transaction.transferTransactonInstance(20.00, AccountFixtures.typicalAccount(), AccountFixtures.typicalAccountTwo());
	}
	
	public static Transaction badTransfer() {
		return Transaction.transferTransactonInstance(30.00, AccountFixtures.typicalAccount(), AccountFixtures.badAccountName());
	}
	
	public static Transaction typicalLodge() {
		return Transaction.lodgeTransactionInstance(AccountFixtures.typicalAccount(), 10.00);
	}
	
	public static Transaction badLodge() {
		return Transaction.lodgeTransactionInstance(AccountFixtures.typicalAccount(), new Double("invalid amount"));
	}
	
	public static Transaction typicalWithdraw() {
		return Transaction.lodgeTransactionInstance(AccountFixtures.typicalAccount(), 10.00);
	}
	
	public static Transaction badWithdraw() {
		return Transaction.withdrawTransactionInstance(AccountFixtures.typicalAccount(), 99999.00);
	}
	
	public static Transaction cloneTypicalTransfer() {
		return typicalTransfer();
	}
}
