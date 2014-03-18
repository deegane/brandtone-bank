package com.brandtone.bank.cli.util;

/**
 * CLI Menus
 * 
 * Wanted to read in files for menus but
 * had issues reading file from classpath
 * in jar
 * 
 * @author deegane
 */
public class MenuUtil {
	
	public final static StringBuilder MAIN_MENU = new StringBuilder("")
		.append("\nWelcome to Brandtone Banking")
		.append("\n\n 1. Accounts (Create new acccount)")
		.append("\n 2. Lodgements (Lodge to account)")
		.append("\n 3. TransferMoney (Transfer money between two accounts)")
		.append("\n 4. Transactions (View Transactions)")
		.append("\n\n Press 0 at anytime to return to Main Menu")
		.append("\n\n Key in selectin at prompt:");
}


