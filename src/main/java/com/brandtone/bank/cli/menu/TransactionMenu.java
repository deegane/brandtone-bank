package com.brandtone.bank.cli.menu;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandtone.bank.cli.Command;
import com.brandtone.bank.domain.Transaction;
import com.brandtone.bank.service.BankingService;

@Component
public class TransactionMenu {

	public final static StringBuilder TRANSACTIONS_MENU = new StringBuilder("")
	.append("-----------------------------------------------------------------------------")
	.append("\n		View Transactions of an Account\n");
	
	public final static StringBuilder TRANSACTIONS_OPTIONS = new StringBuilder("")
	.append("0. 	Main Menu")
	.append("\n1.	View Transactions")
	.append("\n2.	View Mini Statement (previous month)")
	.append("\n-----------------------------------------------------------------------------");
	
	private Console console = System.console();
	
	@Autowired
	BankingService bankingService;
	
	@Autowired
	MainMenu mainMenu;
	
	public enum Options
	{
		MAINMENU("0"), TRANSACTION("1"),MINI("2"),QUIT("q");
		
		private String selection;
		
		private Options(String selection) {
			this.selection = selection;		
		}	
		public String id() { return selection; }
	}
	
	Map<String, Command> cmdMap = new HashMap<>();
	{
	    cmdMap.put(Options.TRANSACTION.id(), new Command() {
	        public void runCommand() { viewTransaction(); };
	    });
	    cmdMap.put(Options.MINI.id(), new Command() {
	        public void runCommand() { miniStatement(); };
	    });
	    cmdMap.put(Options.MAINMENU.id(), new Command() {
	        public void runCommand() { mainMenu.run(); };
	    });
	    cmdMap.put(Options.QUIT.id(), new Command() {
	        public void runCommand() { System.exit(0); };
	    });
	}
	
	public void run() {
		
		System.out.println(TRANSACTIONS_MENU);
		
		System.out.println(TRANSACTIONS_OPTIONS);
		
		String selection = console.readLine();
		
		if(cmdMap.containsKey(selection)) {
			cmdMap.get(selection).runCommand();
		} else {
			run();
		}
		
	}

		/**
		 * View Transactions for an account
		 */
		private void viewTransaction() {
			System.out.println("\nEnter from Account Number (make sure accounts exists)");
			System.out.println("Ex: 5678");
			
			String account = console.readLine();
			
			long accountNumber = Long.valueOf(account);
		

			Set<Transaction> transactions = bankingService.viewTransactionsByAccount(accountNumber);
			
			System.out.println("Transactions returned: \n" );
			
			for(Transaction tx : transactions) {
				System.out.println(tx);
			}
		
			run();
					
		}
		
		/**
		 * View Transactions for an account
		 */
		private void miniStatement() {
			System.out.println("\nEnter from Account Number to view previous month of transactions (make sure accounts exists)");
			System.out.println("Ex: 5678");
			
			String account = console.readLine();
			
			long accountNumber = Long.valueOf(account);
		

			Set<Transaction> transactions = bankingService.viewMiniStatement(accountNumber);
			
			System.out.println("Transactions returned: \n" );
			
			for(Transaction tx : transactions) {
				System.out.println(tx);
			}
		
			run();
					
		}
	}