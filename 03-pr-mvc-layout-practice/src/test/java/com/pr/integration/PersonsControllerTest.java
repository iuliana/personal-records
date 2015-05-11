package com.pr.integration;

import com.pr.PersonsController;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 3/24/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/mvc-config.xml",
        "classpath:spring/app-service-config.xml", "classpath:spring/test-db-config.xml"})
public class PersonsControllerTest {

    @Autowired
    private PersonsController personsController;

    @Before
    public void setUp() {
        assertNotNull(personsController);
    }

    @Test
    public void list() {
        Model model = new BindingAwareModelMap();
        String view = personsController.list(model);
        assertEquals("persons/list", view);
        assertNotNull(model.asMap().get("persons"));
        assertTrue(((List<Person>)model.asMap().get("persons")).size() > 0);
    }


    @Test
    public void show() throws NotFoundException {
        Model model = new BindingAwareModelMap();
        String view = personsController.show(1L, model);
        assertEquals("persons/show", view);
        assertNotNull(model.asMap().get("person"));

    }

}
