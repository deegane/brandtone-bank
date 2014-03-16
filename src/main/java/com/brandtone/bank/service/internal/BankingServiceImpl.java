package com.brandtone.bank.service.internal;

import java.util.List;

import org.apache.log4j.Logger;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.service.BankingService;

/**
 * Implement Banking Service
 * 
 * @author deegane
 */

public class BankingServiceImpl implements BankingService  {
	
	//TODO: slf4j not working correctly for unknown reason. Fix later.
	//private static final Logger log = LoggerFactory.getLogger(BankingServiceImpl.class);
	
	private static final Logger log = Logger.getLogger(BankingServiceImpl.class);
	
	@Override
	public Account createAccount(Account account) {
		log.info("createAccount(account={}) " + account.getNumber());
		
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
