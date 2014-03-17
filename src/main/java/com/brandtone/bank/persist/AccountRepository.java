package com.brandtone.bank.persist;

import org.springframework.data.repository.CrudRepository;

import com.brandtone.bank.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {


}
