package com.brandtone.bank.service.internal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.h2.value.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.domain.Transaction;
import com.brandtone.bank.persist.AccountRepository;
import com.brandtone.bank.persist.TransactionRepository;
import com.brandtone.bank.service.BankingService;
import com.google.common.collect.Lists;

/**
 * Implement Banking Service
 * 
 * @author deegane
 */
@Service
@Transactional(rollbackOn = Exception.class)
public final class BankingServiceImpl implements BankingService  {
	
	private static final Logger log = LoggerFactory.getLogger(BankingServiceImpl.class);
	
	private final AccountRepository accountRepository;
	
	private final TransactionRepository transactionRepository;
	
	@Autowired
	public BankingServiceImpl(final AccountRepository accountRepository, final TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}
		
	/**
	 * Create a new Account	
	 * 
	 * @param account
	 */
	@Override
	public Account createAccount(final Account account) {
		log.info("createAccount(account={})", account);
		return accountRepository.save(account);
	}
	
	/**
	 * Delete Account
	 * 
	 * @param account : Account
	 */
	@Override
	public void deleteAccount(final Account account) {
		log.info("deleteAccount(id={})", account.getId());
		accountRepository.delete(account);	
	}

	/**
	 * Find Account
	 * 
	 * @param accountNumber : Account Id
	 */	
	@Override
	public Account findAccount(final long id) {
		log.info("findAccount(accountId={})", id);		
		return accountRepository.findOne(id);
	}
	
	/**
	 * Find Account
	 * 
	 * @param accountNumber : Account Id
	 */	
	@Override
	public Account findAccount(final Account account) {
		log.info("findAccount(accountId={})", account.getId());		
		return accountRepository.findOne(account.getId());
	}
	
	/**
	 * Find Account by Account number
	 * 
	 * @param account : Account
	 */
	@Override
	public Account findAccountByNumber(final Account account) {
		log.info("findAccount(accNumber={})", account.getNumber());	
		return accountRepository.findByAccountNumber(account.getNumber());
	}
	
	/**
	 * Find Account by Account number
	 * Tra
	 * @param accountNumber: The account number
	 */
	@Override
	public Account findAccountByNumber(final long accountNumber) {
		log.info("findAccount(accNumber={})", accountNumber);	
		return accountRepository.findByAccountNumber(accountNumber);
	}
	
	/**
	 * Returns all accounts from database
	 * 
	 */
	@Override
	public List<Account> findAllAccounts() {
		log.info("findAllAccounts()");	
		return Lists.newArrayList(accountRepository.findAll());	
	}

	/**
	 * Make a lodgement
	 * 
	 * @param account : Account
	 */
	@Override
	public Account lodge(final long accNumber, final double amount) {
		log.info("lodge( accountNumber={} , amount={} ", accNumber,amount);
		
		Account accountToLodge = accountRepository.findByAccountNumber(accNumber);
		accountToLodge.lodge(amount);
		
		Transaction transfer = Transaction.lodgeTransactionInstance(accountToLodge, amount);
		transfer.setFromAcc(accountToLodge);
		
		// update account transaction set
		Set<Transaction> transactions = accountToLodge.getTransactions();
		transactions.add(transfer);
		accountToLodge.setTransactions(transactions);
		
		transactionRepository.save(transfer);
		
		return accountRepository.save(accountToLodge);
	}
	
	/**
	 * Make a withdrawal
	 * 
	 * @param account : Account
	 */
	@Override
	public Account withdraw(final long accountNumber, final double amount) {
		
		Account accountToWithDraw = accountRepository.findByAccountNumber(accountNumber);
		accountToWithDraw.withdraw(amount);
		
		Transaction withdraw = Transaction.withdrawTransactionInstance(accountToWithDraw, amount);
		withdraw.setFromAcc(accountToWithDraw);
		
		// update account transaction set
		Set<Transaction> transactions = accountToWithDraw.getTransactions();
		transactions.add(withdraw);
		accountToWithDraw.setTransactions(transactions);
		
		transactionRepository.save(withdraw);
		
		return accountRepository.save(accountToWithDraw);
	}


	/**
	 * Transfer amount from one account to another
	 * 
	 * @param fromAccount	: Account initiating transfer and account to debit
	 * @param toAccount		: Account to credit 
	 * @param amount  		: Amount to transfer
	 */
	@Override
	public void transfer(final Account fromAccount, final Account toAccount, double amount) {
		
		log.info("Transfer of amount {} from account(id={}) to account(id={})",amount, fromAccount, toAccount);
		
		fromAccount.withdraw(amount);
		toAccount.lodge(amount);
		
		Transaction transfer = Transaction.transferTransactonInstance(amount, fromAccount, toAccount);
		
		// update account transaction set
		Set<Transaction> transactions = fromAccount.getTransactions();
		transactions.add(transfer);
		fromAccount.setTransactions(transactions);
		
		transfer.setFromAcc(fromAccount);
		transactionRepository.save(transfer);
	}
	
	/**
	 * Return All Transactions on System
	 */
	@Override
	public List<Transaction> viewAllTransactions() {
		return Lists.newArrayList(transactionRepository.findAll());
	}
	
	
	/**
	 * View Transacations for an Account in a given Date range
	 * 
	 */
	@Override
	public Set<Transaction> viewTransactionsByAccount(Account account, Date fromDate,
			Date toDate) {
		
		log.info("getTransactions( from={}, to={}", fromDate, toDate);

		return account.getTransactions();
	}
	
	/**
	 * View Transacations for an Account in a given Date range
	 * 
	 */
	@Override
	public Set<Transaction> viewTransactionsByAccount(final long accountNumber, final Date fromDate,
			final Date toDate) {
		
		log.info("getTransactions( from={}, to={}", fromDate, toDate);
		
		Account account = accountRepository.findByAccountNumber(accountNumber);
		
		return account.getTransactions();
	}
}