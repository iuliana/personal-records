package com.pr.unit;

import com.pr.PersonsController;
import com.pr.PersonsListController;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import com.pr.repos.PersonRepo;
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
    private PersonRepo repoMock;

    @Before
    public void setUp(){
        personsListController = new PersonsListController(repoMock);
    }

    @Test
    public void list() {
        when(repoMock.findAll()).thenReturn(new ArrayList<>());

        Model model = new BindingAwareModelMap();
        String view = personsListController.list(model);
        assertEquals("persons/list", view);
        assertNotNull(model.asMap().get("persons"));
    }

}
