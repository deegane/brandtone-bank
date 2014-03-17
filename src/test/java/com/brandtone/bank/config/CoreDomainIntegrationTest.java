package com.brandtone.bank.config;


import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brandtone.bank.config.CoreConfig;
import com.brandtone.bank.config.PersistanceConfig;
import com.brandtone.bank.domain.Account;
import com.brandtone.bank.domain.Transaction;
import com.brandtone.bank.fixtures.AccountFixtures;
import com.brandtone.bank.service.BankingService;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistanceConfig.class, CoreConfig.class})
public class CoreDomainIntegrationTest {

	@Autowired
	BankingService bankingService;
	

	@Test
	public void createAccount() throws Exception {
		
		Account account = bankingService.createAccount(
				AccountFixtures.typicalAccount() );
		
		assertNotNull(account);	
	}
	
	@Test
	public void retrieveAccounts() throws Exception {
		List<Account> accounts = bankingService.findAllAccounts();
		
		assertNotNull(accounts);
	}
	
	@Test
	public void retrieveMiniStatement() throws Exception {
		Set<Transaction> transactions = bankingService.viewMiniStatement(AccountFixtures.typicalAccount());
		
		assertNotNull(transactions);
	}
}