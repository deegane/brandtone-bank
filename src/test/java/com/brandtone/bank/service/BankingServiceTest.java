package com.brandtone.bank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.brandtone.bank.config.CoreConfig;
import com.brandtone.bank.config.PersistanceConfig;
import com.brandtone.bank.domain.Account;
import com.brandtone.bank.fixtures.AccountFixtures;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class, PersistanceConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BankingServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BankingServiceTest.class);
	
    @Autowired
	private BankingService bankingService;
	
	@Test
	public void createAccount() {
		
		Account account = AccountFixtures.typicalAccount();
		Account savedAccount = bankingService.createAccount(account);
		
		log.debug("Account to save",account);
		log.debug("Account saved",savedAccount);
		
		assertEquals(account, savedAccount);
	}
	
	@Test
	public void deleteAccount() {
		bankingService.deleteAccount(AccountFixtures.typicalAccount());
	}
	
	@Test
	public void lodge() {
		
		long accountNumber = 1; 
		double amount = 20.00;
		
		Account account = bankingService.lodge(accountNumber, amount);
		assertNotNull(account);
	}
	
	@Test
	public void withdraw() {
		
		long accountNumber = 1; 
		double amount = 20.00;
		
		Account account = bankingService.lodge(accountNumber, amount);
		assertNotNull(account);

	}
	
	@Test
	public void findAccount() {
		
		long accNumber = 1111;
		
		Account account = bankingService.findAccount(accNumber);		
		assertEquals(account.getNumber(), accNumber);
	}
	
	@Test
	public void findAllAccounts() {
		List<Account> accounts = bankingService.findAllAccounts();
		Assert.notEmpty(accounts);
	}
	
	public void transfer() {
		//TODO
	}
	
	 public void viewtransactions() {
		 //TODO
	 }
}
