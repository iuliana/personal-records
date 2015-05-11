package com.pr.unit;

import com.pr.PersonsController;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
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
import static org.mockito.Mockito.when;

/**
 * Created by iuliana.cosmina on 3/23/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonsControllerTest {

    private static final Long TEST_ID = 1L;

    private PersonsController personsController;

    @Mock
    private PersonManager personManager;

    @Before
    public void setUp() {
        personsController = new PersonsController(personManager);
    }

    @Test
    public void list() {
        when(personManager.findAll()).thenReturn(new ArrayList<>());

        Model model = new BindingAwareModelMap();
        String view = personsController.list(model);
        assertEquals("persons/list", view);
        assertNotNull(model.asMap().get("persons"));
    }


    @Test
    public void show() throws NotFoundException {
        when(personManager.findById(TEST_ID)).thenReturn(new Person());

        Model model = new BindingAwareModelMap();
        String view = personsController.show(TEST_ID, model);
        assertEquals("persons/show", view);
        assertNotNull(model.asMap().get("person"));
    }

    @Test(expected = NotFoundException.class)
    public void dontShow() throws NotFoundException {
        when(personManager.findById(TEST_ID)).thenReturn(null);

        Model model = new BindingAwareModelMap();
        personsController.show(TEST_ID, model);
    }

}
