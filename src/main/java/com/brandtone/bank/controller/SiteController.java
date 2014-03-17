package com.brandtone.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brandtone.bank.domain.Account;
import com.brandtone.bank.service.BankingService;

@Controller
@RequestMapping("/")
public class SiteController {
	
	private static final Logger log = LoggerFactory.getLogger(SiteController.class);
	
	@Autowired
    BankingService bankingService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getAccounts() {
		
		log.debug("SiteController.getAccounts()");
		
		List<Account> accounts = bankingService.findAllAccounts();
		log.debug("accounts: {}", accounts);
	
		return accounts.toString();
	}
}
