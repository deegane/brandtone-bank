package com.brandtone.bank.service.internal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.persist.AccountRepository;
import com.brandtone.bank.service.BankingService;
import com.google.common.collect.Lists;

/**
 * Implement Banking Service
 * 
 * @author deegane
 */
@Service
@Transactional
public class BankingServiceImpl implements BankingService  {
	
	private static final Logger log = LoggerFactory.getLogger(BankingServiceImpl.class);
	
	private final AccountRepository accountRepository;
	
	@Autowired
	public BankingServiceImpl(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account createAccount(Account account) {
		log.info("createAccount(account={})", account);
		return accountRepository.save(account);
	}
	
	@Override
	public void deleteAccount(Account account) {
		log.info("deleteAccount(id={})", account.getId());
		accountRepository.delete(account);	
	}

	@Override
	public Account findAccount(long accountNumber) {
		log.info("findAccount(accountNumber={})", accountNumber);		
		return accountRepository.findOne(accountNumber);
	}

	@Override
	public List<Account> findAllAccounts() {
		log.info("findAllAccounts()");	
		return Lists.newArrayList(accountRepository.findAll());	
	}

	@Override
	public Account lodge(long accountNumber, double amount) {
		log.info("lodge( accountNumber={} , amount={} ", accountNumber,amount);
		
		Account accountToLodge = accountRepository.findOne(accountNumber);
		accountToLodge.lodge(amount);
		
		return accountRepository.save(accountToLodge);
		
		//TODO: Record transaction
	}
	
	
	@Override
	public Account withdraw(long accountNumber, double amount) {
		Account accountToWithDraw = accountRepository.findOne(accountNumber);
		accountToWithDraw.lodge(amount);
		
		return accountRepository.save(accountToWithDraw);
	}

	

	@Override
	public void transfer(Account fromAccount, Account toAccount, double amount) {
		
		log.info("Transfer of amount {} from account(id={}) to account(id={})",amount, fromAccount, toAccount);
		
		fromAccount.withdraw(amount);
		toAccount.lodge(amount);
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		//TODO: Record Transaction
	}
	
	
}