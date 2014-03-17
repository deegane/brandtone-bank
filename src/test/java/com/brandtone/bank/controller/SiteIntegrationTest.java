//package com.brandtone.bank.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.brandtone.bank.fixtures.AccountFixtures;
//import com.brandtone.bank.service.BankingService;
//
//public class SiteIntegrationTest {	
//
//	MockMvc mockMvc;
//		
//	@InjectMocks
//	SiteController controller;
//	
//	@Autowired
//	BankingService bankingService;
//	
//	String RESPONSE_BODY = "";
//	
//	@Before
//	public void setup() {
//		
//		MockitoAnnotations.initMocks(this);
//		
//        RESPONSE_BODY = AccountFixtures.typicalAccounts().toString();
//		
//		mockMvc = standaloneSetup(controller).build();
//		
//		when(bankingService.findAllAccounts()).thenReturn(AccountFixtures.typicalAccounts());
//	
//	}
//	
//	//Not testin
//	public void thatTextReturned() throws Exception {
//		
//		mockMvc.perform(get("/"))
//		.andDo(print())
//		.andExpect(content().string(RESPONSE_BODY));
//	}
//	
//}
