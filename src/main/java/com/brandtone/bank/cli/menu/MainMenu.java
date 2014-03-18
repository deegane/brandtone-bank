package com.brandtone.bank.cli.menu;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandtone.bank.cli.Command;

@Component
public class MainMenu {

	public final static StringBuilder MAIN_MENU = new StringBuilder("")
	.append("\nWelcome to Brandtone Banking")
	.append("\n\n 1. Accounts (Create new acccount)")
	.append("\n 2. Lodgements (Lodge to account)")
	.append("\n 3. TransferMoney (Transfer money between two accounts)")
	.append("\n 4. Transactions (View Transactions)")
	.append("\n\nPress 0 at anytime to return to Main Menu or type 'q' to quit")
	.append("\n\n Key in selectin at prompt:");
	
	private Console console = System.console();
	
	@Autowired
	private AccountMenu accountMenu;
	
	public enum Options
	{
		MAIN("0"),ACCOUNT("1"), LODGE("2"), TRANSFER("3"), TRANSACTIONS("4"),QUIT("q");
		
		private String selection;
		
		private Options(String selection) {
			this.selection = selection;		
		}	
		public String id() { return selection; }
	}
	
		Map<String, Command> cmdMap = new HashMap<>();
		{
		    cmdMap.put(Options.ACCOUNT.id(), new Command() {
		        public void runCommand() { accountMenu.run(); };
		    });
		    cmdMap.put(Options.LODGE.id(), new Command() {
		        public void runCommand() { accountMenu.run(); };
		    });
		    cmdMap.put(Options.TRANSFER.id(), new Command() {
		        public void runCommand() { accountMenu.run(); };
		    });
		    cmdMap.put(Options.TRANSACTIONS.id(), new Command() {
		        public void runCommand() { accountMenu.run(); };
		    });
		    cmdMap.put(Options.QUIT.id(), new Command() {
		        public void runCommand() { accountMenu.run(); };
		    });
		}
		
		public void run()  {	
			
			System.out.println(MAIN_MENU);
			String selection = console.readLine();
			
			if(cmdMap.containsKey(selection)) {
				cmdMap.get(selection).runCommand();
			} else {
				run();
			}
		}	
	}