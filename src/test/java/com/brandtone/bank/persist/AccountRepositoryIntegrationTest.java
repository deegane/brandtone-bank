package com.brandtone.bank.persist;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.brandtone.bank.config.PersistanceConfig;
import com.brandtone.bank.domain.Account;
import com.brandtone.bank.fixtures.AccountFixtures;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistanceConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AccountRepositoryIntegrationTest {

	@Autowired
	AccountRepository accountRepository;

	@Test
	public void thatItemIsInsertedIntoRepoWorks() throws Exception {
		
		Account accountToSave = AccountFixtures.typicalAccount();
		
		accountRepository.save(accountToSave);
		
		Account retrievedAccount = accountRepository.findOne(accountToSave.getId());
		
		assertEquals(accountToSave, retrievedAccount);
		
	}
}
