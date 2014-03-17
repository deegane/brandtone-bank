package com.brandtone.bank.service.internal;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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
	public Account lodge(final long accountNumber, final double amount) {
		log.info("lodge( accountNumber={} , amount={} ", accountNumber,amount);
		
		Account accountToLodge = accountRepository.findOne(accountNumber);
		accountToLodge.lodge(amount);
		
		Transaction transfer = Transaction.lodgeTransactionInstance(accountToLodge, amount);
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
		Account accountToWithDraw = accountRepository.findOne(accountNumber);
		accountToWithDraw.lodge(amount);
		
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
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		Transaction transfer = Transaction.transferTransactonInstance(amount, fromAccount, toAccount);
		
		Transaction saved = transactionRepository.save(transfer);
		
		List<Transaction> tx = Lists.newArrayList(transactionRepository.findAll());
		
	}
	
	
	/**
	 * View Transacations for an Account in a given Date range
	 * 
	 */
	@Override
	public List<Transaction> viewTransactions(Date from,
			Date to) {
		
		log.info("getTransactions( from={},  to={}", from, to);
		
		//TODO: need to replace query below
		List<Transaction> transactions = Lists.newArrayList(transactionRepository.findAll());
		
		//TODO: Custom query required to search date range	
		//TODO: need to perform search on an account
		
		return transactions;
	}
}