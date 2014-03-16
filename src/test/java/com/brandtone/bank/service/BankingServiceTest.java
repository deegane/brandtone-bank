package com.brandtone.bank.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.fixtures.AccountFixtures;

public class BankingServiceTest {

	//TODO: slf4j not working correctly. Fix later. 
	private static final Logger log = Logger.getLogger(BankingServiceTest.class);
	
	@Mock
	private BankingService bankingService;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		when(bankingService.createAccount(any(Account.class))).thenReturn(AccountFixtures.typicalAccount());
	}
		
	@Test
	public void createAccount() {
		
		Account account = AccountFixtures.typicalAccount();
		Account savedAccount = bankingService.createAccount(account);
	
		log.debug("Account to save" + account.toString());
		log.debug("Account saved" + account.toString());
		
		assertEquals(account, savedAccount);
	}
	
	
	/*
	 TODO:
	
	public void deleteAccount(Account account);
	
	public Account findAccount(long number); 
	
	public List<Account> findAllAccounts();
	
	public Account lodge(Long accountNumber, double amount);
	
	public Account withdraw(Long accountNumber, double amount);

	public void transfer(Account fromAccount, Account toAccount, double amount);
	
	 //viewtransactions
	 
	 */
	
}
