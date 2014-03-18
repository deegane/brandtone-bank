package com.brandtone.bank.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.domain.Transaction;

public interface BankingService {

	public Account createAccount(final Account account);
	
	public void deleteAccount(final Account account);
	
	public Account findAccount(final long id); 
	
	public Account findAccount(final Account account); 
	
	public Account findAccountByNumber(final Account accNumber);
	
	public Account findAccountByNumber(final long accNumber);
	
	public List<Account> findAllAccounts();
	
	public Account lodge(final long accountNumber, final double amount);
	
	public Account withdraw(final long accountNumber, final double amount);
	
	public void transfer(final Account fromAccount, final Account toAccount, double amount);
	
	public void transfer(final long fromAccount, final long toAccount, double amount);
	
	public Set<Transaction> viewAllTransactions();
	
	public Set<Transaction> viewTransactionsByAccount(Account account);
	
	public Set<Transaction> viewMiniStatement(final Account account);
	
	public Set<Transaction> viewMiniStatement(final long account);
	 
	public Set<Transaction> viewTransactionsByAccount(final Account account, final Date searchFrom);
	
	public Set<Transaction> viewTransactionsByAccount(final long accountNumber, final Date fromSearchFrom);
	
	public Set<Transaction> viewTransactionsByAccount(final long accountNumber);
}
