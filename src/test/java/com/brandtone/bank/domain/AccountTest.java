package com.brandtone.bank.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brandtone.bank.fixtures.AccountFixtures;

/**
 * Simple domain object tests
 * 
 * @author deegane
 *
 */
public class AccountTest {
	
	private static final Logger log = LoggerFactory.getLogger(AccountTest.class);
	

	@Test
	public void testAccountEquals() {
	
		Account accountOne = AccountFixtures.typicalAccount();
		Account accountTwo = AccountFixtures.typicalAccountClone();
		
		log.debug("accountOne", accountOne);
		log.debug("accountTwo", accountTwo);
		
		assertEquals(accountOne, accountTwo);	
	}
	
	@Test
	public void testAccountNotEquals() {
	
		Account accountOne = AccountFixtures.typicalAccount();
		Account accountTwo = AccountFixtures.typicalAccountTwo();
		
		assertNotEquals(accountOne, accountTwo);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAccountInValidName() {
		Account invalidAccountName = AccountFixtures.badAccountName();	
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAccountInValidBalance() {
		Account invalidAccountBalance = AccountFixtures.badAccountBalance();	
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAccountInValidAddress() {
		Account invalidAccountAddress = AccountFixtures.badAccountAddress();	
	}
	

}
