package com.brandtone.bank.persist;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brandtone.bank.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	@Query("SELECT a from Account a WHERE a.accNumber = ?")
	public Account findByAccountNumber(long accNumber);

}
