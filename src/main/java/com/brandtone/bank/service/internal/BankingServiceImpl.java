package com.brandtone.bank.service.internal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.service.BankingService;

/**
 * Implement Banking Service
 * 
 * @author deegane
 */

public class BankingServiceImpl implements BankingService  {
	
	private static final Logger log = LoggerFactory.getLogger(BankingServiceImpl.class);
	
	@Override
	public Account createAccount(Account account) {
		log.info("createAccount(account={})", account.getNumber());
		
		//TODO: Retreive account from Account Repository
		return null;
	}
	
	@Override
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account findAccount(long number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account lodge(Long accountNumber, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transfer(Account fromAccount, Account toAccount, double amount) {
		// TODO Auto-generated method stub
		
	}

}
