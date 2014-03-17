package com.brandtone.bank.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {PersistanceConfig.class, CoreConfig.class,
        WebConfig.class })
public class WebDomainIntegrationTest {

	// values taken from data.sql
    private static final String CHECK_ONE = "Dasher";
    private static final String CHECK_TWO = "Dancer";
    private static final String CHECK_THREE = "Prancer";

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void thatTextReturned() throws Exception {
        mockMvc.perform(get("/"))
        .andDo(print())
        .andExpect(content().string(containsString(CHECK_ONE)))
        .andExpect(content().string(containsString(CHECK_TWO)))
        .andExpect(content().string(containsString(CHECK_THREE)));

    }

}