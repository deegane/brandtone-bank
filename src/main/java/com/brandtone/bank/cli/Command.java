package com.brandtone.bank.cli;

import static com.brandtone.bank.cli.util.MenuUtil.MAIN_MENU;

import java.io.Console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.brandtone.bank.service.BankingService;

/**
 * Command Line Menu App
 * 
 * @author deegane
 *
 */
@Component
public class Command implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(Command.class);

	public enum MainMenu
	{
		ACCOUNT("1"), LODGE("2"), TRANSFER("3"), TRANSACTIONS("4"),MAINMENU("0");
		
		private String selection;
		
		private MainMenu(String selection) {
			this.selection = selection;
		}	
		 
		public String id() { return selection; }
		public void setSeletion (String selection) {
			this.selection = selection;
		}
	}
	
	@Autowired
	private BankingService bankingService;
	
	private Console console = System.console();
	
	@Override
	public void run(String... arg0) throws Exception {
	
		mainMenu();
	}

	
	private void mainMenu()  {
		
		System.out.println(MAIN_MENU);
		String selection = System.console().readLine();
		
		if(selection.equals(MainMenu.ACCOUNT.id())) {
			accountMenu();
		} else if(selection.equals(MainMenu.LODGE.id())) {
			lodgeMenu();
		} else if(selection.equals(MainMenu.TRANSFER.id())) {
			transferMenu();
		} else if(selection.equals(MainMenu.TRANSACTIONS.id())) {
			transactionsMenu();
		}else if(selection.equals(MainMenu.MAINMENU.id())) {
			mainMenu();
		} else {
			mainMenu();
		}
		
	}
	
	private void accountMenu() {
		System.out.println("accountMenu");
	}
	
	private void lodgeMenu() {
		System.out.println("lodge");
	}
	
	private void transferMenu() {
		System.out.println("transfer");
	}
	
	private void transactionsMenu() {
		System.out.println("transactionsMenu");
	}
	
}
