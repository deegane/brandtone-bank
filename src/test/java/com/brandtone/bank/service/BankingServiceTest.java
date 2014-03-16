package com.brandtone.bank.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.fixtures.AccountFixtures;

public class BankingServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BankingServiceTest.class);
	
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
	
		log.debug("Account to save" + account);
		log.debug("Account saved" + account);
		
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
