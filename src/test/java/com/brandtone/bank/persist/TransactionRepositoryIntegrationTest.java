package com.brandtone.bank.persist;

import static junit.framework.TestCase.assertEquals;

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

import com.brandtone.bank.config.PersistanceConfig;
import com.brandtone.bank.domain.Account;
import com.brandtone.bank.domain.Transaction;
import com.brandtone.bank.fixtures.AccountFixtures;
import com.brandtone.bank.fixtures.TransactionFixtures;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistanceConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TransactionRepositoryIntegrationTest {

	private static final Logger log = LoggerFactory.getLogger(TransactionRepositoryIntegrationTest.class);
	
	@Autowired
	TransactionRepository transactionRepository;

	@Test
	public void thatItemIsInsertedIntoRepoWorks() throws Exception {
		
		Transaction transactionToSave = TransactionFixtures.typicalTransfer();
		
		transactionRepository.save(transactionToSave);
		
		Transaction retrievedTransaction = transactionRepository.findOne(transactionToSave.getId());
		
		assertEquals(transactionToSave, retrievedTransaction);	
	}
}
