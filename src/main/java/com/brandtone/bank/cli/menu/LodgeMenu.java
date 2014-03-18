package com.brandtone.bank.cli.menu;

import java.io.Console;
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
public class LodgeMenu {

	public final static StringBuilder LODGE_MENU = new StringBuilder("")
	.append("-----------------------------------------------------------------------------")
	.append("\n		Lodge Money to an Account\n");
	
	public final static StringBuilder LODGE_OPTIONS = new StringBuilder("")
	.append("0. 	Main Menu")
	.append("\n1.	Lodge Money")
	.append("\n-----------------------------------------------------------------------------");
	
	private Console console = System.console();
	
	@Autowired
	BankingService bankingService;
	
	@Autowired
	MainMenu mainMenu;
	
	public enum Options
	{
		MAINMENU("0"), LODGE("1"),QUIT("q");
		
		private String selection;
		
		private Options(String selection) {
			this.selection = selection;		
		}	
		public String id() { return selection; }
	}
	
	Map<String, Command> cmdMap = new HashMap<>();
	{
	    cmdMap.put(Options.LODGE.id(), new Command() {
	        public void runCommand() { lodge(); };
	    });
	    cmdMap.put(Options.MAINMENU.id(), new Command() {
	        public void runCommand() { mainMenu.run(); };
	    });
	    cmdMap.put(Options.QUIT.id(), new Command() {
	        public void runCommand() { System.exit(0); };
	    });
	}
	
	public void run() {
		
		System.out.println(LODGE_MENU);
		
		System.out.println(LODGE_OPTIONS);
		
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
		public void lodge() {
			System.out.println("\nEnter Account Number, amount (make sure account exists)");
			System.out.println("Ex: 1234,20.00\n");
			
			String account = console.readLine();
			
			Iterable<String> accParms = Splitter.on(',')
				       .trimResults()
				       .omitEmptyStrings()
				       .split(account); 
			
			String[] params = Iterables.toArray(accParms, String.class);
			
			long accNumber = Long.valueOf(params[0]);
			double amount = Double.valueOf(params[1]);
			
			
			Account accountToUpdate = bankingService.findAccountByNumber(accNumber);
			System.out.println("\nCurrent Balance: " + accountToUpdate.getBalance());
			
			Account accountUpdated = bankingService.lodge(accNumber, amount);
			System.out.println("\nUpdated Balance: " + accountUpdated.getBalance());
			
			run();		
		}
	}
