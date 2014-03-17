package com.brandtone.bank.service;

import java.util.List;

import com.brandtone.bank.domain.Account;

public interface BankingService {

	public Account createAccount(Account account);
	
	public void deleteAccount(Account account);
	
	public Account findAccount(long number); 
	
	public List<Account> findAllAccounts();
	
	public Account lodge(long accountNumber, double amount);
	
	public Account withdraw(long accountNumber, double amount);
	
	public void transfer(Account fromAccount, Account toAccount, double amount);
	
	//TODO: view transactions
	
}
