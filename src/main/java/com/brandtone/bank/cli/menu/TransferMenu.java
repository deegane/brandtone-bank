package com.brandtone.bank.cli.menu;

import java.io.Console;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandtone.bank.cli.Command;
import com.brandtone.bank.domain.Account;
import com.brandtone.bank.service.BankingService;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

@Component
public class TransferMenu {

	public final static StringBuilder TRANSFER_MENU = new StringBuilder("")
	.append("-----------------------------------------------------------------------------")
	.append("\n		Lodge Money from an Account to an account\n");
	
	public final static StringBuilder TRANSFER_OPTIONS = new StringBuilder("")
	.append("0. 	Main Menu")
	.append("\n1.	Transfer Money")
	.append("\n-----------------------------------------------------------------------------");
	
	private Console console = System.console();
	
	@Autowired
	BankingService bankingService;
	
	@Autowired
	MainMenu mainMenu;
	
	public enum Options
	{
		MAINMENU("0"), TRANSFER("1"),QUIT("q");
		
		private String selection;
		
		private Options(String selection) {
			this.selection = selection;		
		}	
		public String id() { return selection; }
	}
	
	Map<String, Command> cmdMap = new HashMap<>();
	{
	    cmdMap.put(Options.TRANSFER.id(), new Command() {
	        public void runCommand() { transfer(); };
	    });
	    cmdMap.put(Options.MAINMENU.id(), new Command() {
	        public void runCommand() { mainMenu.run(); };
	    });
	    cmdMap.put(Options.QUIT.id(), new Command() {
	        public void runCommand() { System.exit(0); };
	    });
	}
	
	public void run() {
		
		System.out.println(TRANSFER_MENU);
		
		System.out.println(TRANSFER_OPTIONS);
		
		String selection = console.readLine();
		
		if(cmdMap.containsKey(selection)) {
			cmdMap.get(selection).runCommand();
		} else {
			run();
		}
		
	}

		/**
		 * Lodge Money to account
		 */
		public void transfer() {
			System.out.println("\nEnter from Account Number, to accout number, amount (make sure accounts exists)");
			System.out.println("Ex: 5678,9887,20.00\n");
			
			String account = console.readLine();
			
			Iterable<String> accParms = Splitter.on(',')
				       .trimResults()
				       .omitEmptyStrings()
				       .split(account); 
			
			String[] params = Iterables.toArray(accParms, String.class);
			
			long fromAccountNumber = Long.valueOf(params[0]);
			long toAccountNumber = Long.valueOf(params[1]);
			double amount = Double.valueOf(params[2]);
			
			bankingService.transfer(fromAccountNumber, toAccountNumber, amount);
		
			run();
					
		}
	}