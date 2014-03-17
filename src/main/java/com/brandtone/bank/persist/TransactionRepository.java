package com.brandtone.bank.persist;

import org.springframework.data.repository.CrudRepository;

import com.brandtone.bank.domain.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	

}
