package com.brandtone.bank.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.brandtone.bank.cli.menu.MainMenu;

/**
 * Command Line Menu App
 * 
 * @author deegane
 *
 */
@Component
public class CLI implements CommandLineRunner {

	@Autowired
	private MainMenu mainMenu;
	
	@Override
	public void run(String... arg0) throws Exception {
		mainMenu.run();
	}
	
	
}
