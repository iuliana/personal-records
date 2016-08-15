package com.pr.web;

import com.pr.web.PersonsListController;
import com.pr.service.PersonManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by iuliana.cosmina on 3/23/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonsListControllerTest {

    private PersonsListController personsListController;

    @Mock
    private PersonManager personManagerMock;

    @Before
    public void setUp(){
        personsListController = new PersonsListController();
        personsListController.setPersonsController(personManagerMock);
    }

    @Test
    public void list() {
        when(personManagerMock.findAll()).thenReturn(new ArrayList<>());

        Model model = new BindingAwareModelMap();
        String view = personsListController.list(model);
        assertEquals("persons/list", view);
        assertNotNull(model.asMap().get("persons"));
    }

}
