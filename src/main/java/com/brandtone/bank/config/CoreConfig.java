package com.brandtone.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brandtone.bank.persist.AccountRepository;
import com.brandtone.bank.service.BankingService;
import com.brandtone.bank.service.internal.BankingServiceImpl;

@Configuration
public class CoreConfig {
		
	@Bean
	public BankingService bankingService(AccountRepository accountRepository) {
		return new BankingServiceImpl(accountRepository);
	}
	
}
	
	