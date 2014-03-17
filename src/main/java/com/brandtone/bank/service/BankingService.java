package com.brandtone.bank.service;

import java.util.Date;
import java.util.List;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.domain.Transaction;

public interface BankingService {

	public Account createAccount(final Account account);
	
	public void deleteAccount(final Account account);
	
	public Account findAccount(final long number); 
	
	public Account findAccount(final Account account); 
	
	public List<Account> findAllAccounts();
	
	public Account lodge(final long accountNumber, final double amount);
	
	public Account withdraw(final long accountNumber, final double amount);
	
	public void transfer(final Account fromAccount, final Account toAccount, double amount);
	
	public List<Transaction> viewTransactions(final Date from, final Date to);
	
}
