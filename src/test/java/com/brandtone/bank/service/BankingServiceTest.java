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
		
		long id = 1;
		
		Account account = bankingService.findAccount(id);		
		assertEquals(account.getId(), id);
	}
	
	@Test
	public void findAllAccounts() {
		List<Account> accounts = bankingService.findAllAccounts();
		Assert.notEmpty(accounts);
	}
	
	/**
	 * Integration Test (TODO: should really be moved to Integration test class)
	 */
	@Test
	public void transfer() {
		Account fromAccount = AccountFixtures.typicalAccount();
		Account toAccount = AccountFixtures.typicalAccountTwo();
		
		double fromAccountStartBalance = fromAccount.getBalance();
		double toAccountStartBalance = toAccount.getBalance();
		
		double transferAmount = 10.00;
		
		Account fromAccountSaved = bankingService.createAccount(fromAccount);
		Account toAccountSaved = bankingService.createAccount(toAccount);
		
		bankingService.transfer(fromAccountSaved, toAccountSaved, transferAmount);
		
		Account fromAccountUpdated = bankingService.findAccount(fromAccountSaved);
		Account toAccountUpdated = bankingService.findAccount(toAccountSaved);
		
		assertEquals(fromAccountStartBalance, fromAccountUpdated.getBalance() + 10, 0);
		assertEquals(toAccountStartBalance, toAccountUpdated.getBalance() - 10, 0);
	}
	
	 public void viewtransactions() {
		 //TODO
	 }
}
