package com.brandtone.bank.cli.menu;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandtone.bank.cli.Command;
import com.brandtone.bank.domain.Account;
import com.brandtone.bank.service.BankingService;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

/**
 * Account Menu
 * 
 * @author deegane
 *
 */
@Component
public final class AccountMenu {
	
	public final static String ACCOUNT_MENU = new String("Current Accounts on System\n");
	
	public final static StringBuilder ACCOUNT_OPTIONS = new StringBuilder("")
	.append("\n0. Main Menu")
	.append("\n1. Create Account");
	
	private Console console = System.console();
	
	@Autowired
	BankingService bankingService;
	
	@Autowired
	MainMenu mainMenu;
	
	public enum Options
	{
		MAINMENU("0"), CREATE("1"), QUIT("q");
		
		private String selection;
		
		private Options(String selection) {
			this.selection = selection;		
		}	
		public String id() { return selection; }
	}
	
	Map<String, Command> cmdMap = new HashMap<>();
	{
	    cmdMap.put(Options.CREATE.id(), new Command() {
	        public void runCommand() { create(); };
	    });
	    cmdMap.put(Options.MAINMENU.id(), new Command() {
	        public void runCommand() { mainMenu.run(); };
	    });
	    cmdMap.put(Options.QUIT.id(), new Command() {
	        public void runCommand() { System.exit(0); };
	    });
	}
	
	public void run() {
		
		System.out.println(ACCOUNT_MENU);

		
		List<Account> accounts = bankingService.findAllAccounts();
		
		for(Account acc : accounts) {
			System.out.println(acc);
		}
		
		System.out.println(ACCOUNT_OPTIONS);
		
		String selection = console.readLine();
		
		if(cmdMap.containsKey(selection)) {
			cmdMap.get(selection).runCommand();
		} else {
			run();
		}
		
	}
	
	/**
	 * Create Account by entering comma separated values.
	 */
	public void create() {
		
		System.out.println("\nEnter accnumber,name,addresss,phone,balance");
		System.out.println("Ex: 6666,bob,123st,999,20.00\n");
		
		
		
		String account = console.readLine();
		
		Iterable<String> accParms = Splitter.on(',')
			       .trimResults()
			       .omitEmptyStrings()
			       .split(account); 
		
		String[] params = Iterables.toArray(accParms, String.class);
		
		long accNumber = Long.valueOf(params[0]);
		String name = params[1];
		String address = params[2];
		String phone = params[3];
		double balance = Double.valueOf(params[4]);
			
		Account newAccount = Account.newInstance(accNumber, name, address, phone, balance);
		
		Account savedAccount = bankingService.createAccount(newAccount);
		
		System.out.println("\nNew Account created: " + savedAccount);
		
		run();
		
	}
}
