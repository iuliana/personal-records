package com.pr.services;

import com.pr.base.Gender;
import com.pr.ents.Person;
import com.pr.repos.PersonRepo;
import com.pr.service.PersonManagerImpl;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by iuliana.cosmina on 5/11/15.
 * Description: Sample test class showing how services can be tested by using mock repositories.
 */
public class PersonManagerTest {

    @Mock //Creates mock instance of the field it annotates
    private PersonRepo personRepo;

    @InjectMocks     // Instantiates testing object instance and tries to inject fields annotated with @Mock or @Spy into private
    // fields of testing object
    private PersonManagerImpl personManager;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void finalAll() {
        List<Person> expected = new ArrayList<>();
        expected.add(new Person("George", "Lucas", DateTime.parse("1944-05-14").toDate(), Gender.MALE, null));
        expected.add(new Person("George", "R.R.Martin", DateTime.parse("1948-09-20").toDate(), Gender.MALE, null));
        when(personRepo.findAll()).thenReturn(expected);

        List<Person> actual = personManager.findAll();

        assertEquals(expected, actual);

    }

    @Test
    public void testGetById() {
        Person expected = new Person("J.K.", "Rowling", DateTime.parse("1965-07-31").toDate(), Gender.FEMALE, null);
        expected.setId(1L);
        when(personRepo.findOne(1L)).thenReturn(expected);

        Person actual = personManager.findById(1L);

        assertEquals(expected, actual);
    }
}
