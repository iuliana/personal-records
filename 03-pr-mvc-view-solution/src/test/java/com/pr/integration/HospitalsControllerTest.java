package com.pr.integration;

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

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/24/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/mvc-config.xml",
        "classpath:spring/app-dao-config.xml", "classpath:spring/test-db-config.xml"})
public class HospitalsControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        assertNotNull(wac);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/hospitals/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("hospitals"))
                .andExpect(forwardedUrl("/WEB-INF/templates/layout.jsp"));
    }


    @Test
    public void listp() throws Exception {
        mockMvc.perform(get("/hospitals/134181"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("persons"))
                .andExpect(redirectedUrl("/persons/list"));
    }

}
