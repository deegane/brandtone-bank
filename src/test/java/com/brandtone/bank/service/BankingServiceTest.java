package com.brandtone.bank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
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
import com.brandtone.bank.domain.Transaction;
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
	public void createAccount() throws Exception {
		
		Account account = AccountFixtures.typicalAccount();
		Account savedAccount = bankingService.createAccount(account);
		
		log.debug("Account to save",account);
		log.debug("Account saved",savedAccount);
		
		assertEquals(account, savedAccount);
	}
	
	@Test
	public void deleteAccount() throws Exception {
		bankingService.deleteAccount(AccountFixtures.typicalAccount());
	}
	
	@Test
	public void lodge() throws SQLException {
		
		long accountNumber = 1111; 
		double amount = 20.00;
		
		Account account = bankingService.lodge(accountNumber, amount);
		assertNotNull(account);
	}
	
	@Test
	public void withdraw() throws Exception {
		
		long accountNumber = 1111; 
		double amount = 5.00;
		
		Account account = bankingService.withdraw(accountNumber, amount);
		assertNotNull(account);

	}
	
	// Cannot withdraw more than your balance
	@Test(expected=IllegalArgumentException.class)
	public void invalidWithdraw() throws Exception {
		
		long accountNumber = 1111; 
		
		double amount = 99999999999999999.00;
		Account account = bankingService.withdraw(accountNumber, amount);
	}
	
	@Test
	public void findAccount() throws Exception {
		
		long id = 1;
		
		Account account = bankingService.findAccount(id);		
		assertEquals(account.getId(), id);
	}
	
	@Test
	public void findAllAccounts() throws Exception {
		List<Account> accounts = bankingService.findAllAccounts();
		Assert.notEmpty(accounts);
	}
	
	/**
	 * Integration Test (TODO: should really be moved to Integration test class)
	 */
	@Test
	public void transfer() throws Exception {
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
	
	@Test
	 public void viewAlltransactions() throws Exception {
		
		 Set<Transaction> transactions = bankingService.viewAllTransactions();
		 assertNotNull(transactions);
		 
	 }
	 
	/**
	 * Retrieve an Accounts Transaction in Date range and validate
	 * 
	 * Integration test
	 */
	 public void viewTransactionsByAccount() throws Exception {
		 
		 Account account = bankingService.createAccount(AccountFixtures.typicalAccount());
		 
		 bankingService.lodge(account.getNumber(), 20.00);
		 bankingService.withdraw(account.getNumber(), 10.00);
		 
		 Account updatedAccount = bankingService.findAccount(account);
		 
		 DateTime threeMonths = new DateTime().minusMonths(3);
		 
		 Set<Transaction> updatedTransactions  = bankingService.viewTransactionsByAccount(updatedAccount, 
				 threeMonths.toDate());
		 
		 assertNotNull(updatedTransactions);
		 
		 for(Transaction tx : updatedTransactions) {
			 
			 log.debug("tx:{}",tx.getId() );
			 
			 assertEquals(account.getId(), tx.getFromAcc().getId());			 
			 
			 DateTime txDate = new DateTime( tx.getTransactionDate());
			 boolean isAfter = txDate.isAfter(threeMonths);
			 
			 assertEquals(true, isAfter);
		 }	
	 }
}
