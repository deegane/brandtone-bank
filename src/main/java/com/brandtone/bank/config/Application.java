package com.brandtone.bank.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
//
//	@Autowired
//	BankingService bankingService;
//	
//	public void CLI(){
//		 Command cmd = new Command();
//	        cmd.startCLI();
//	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        
    }
}