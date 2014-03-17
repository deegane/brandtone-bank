package com.brandtone.bank.persist;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brandtone.bank.domain.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	
	@Query("SELECT t from Transaction t where t.fromAccount = ? AND t.transactionDate > ?")
	public Set<Transaction>findByDate(long accNumber, Date date);
 
}
