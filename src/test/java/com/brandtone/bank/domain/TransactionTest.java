package com.brandtone.bank.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.brandtone.bank.fixtures.TransactionFixtures;

public class TransactionTest {
	
	@Test
	public void testValidTransfer() {
		Transaction validTransfer = TransactionFixtures.typicalTransfer();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInValidTransfer() {
		Transaction invalidTransfer = TransactionFixtures.badTransfer();
	}
	
	// wont test notEquals because of now date var
	@Test
	public void testEquals() {
		Transaction transfer = TransactionFixtures.typicalTransfer();
		Transaction transferClone = TransactionFixtures.cloneTypicalTransfer();
		
		assertEquals(transfer, transferClone);
	}
	

	//TODO: Add other fixture test cases
}
