package com.pr.repos;

import com.pr.base.Gender;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
public class PersonRepoTest extends RepoTestCase {
    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    private Hospital hospital;

    @Before
    public void init() {
        assertNotNull(hospitalRepo);
        hospital = HospitalRepoTest.of("121412", "Maryland", "Maryland Central");
        hospital = hospitalRepo.save(hospital);

        assertNotNull(personRepo);
        personRepo.save(of("Julie", "Andrews", "1935-10-01", Gender.FEMALE, hospital));
        personRepo.save(of("Ada", "Levelace", "1815-12-10", Gender.FEMALE, hospital));
        personRepo.save(of("Peter", "Parker", "1980-04-13", Gender.MALE, hospital));
    }

    @Test
    public void update() {
        List<Person> persons = personRepo.getByFirstnameOrLastname("Ada");
        assertTrue(persons.size() == 1);
        Person person = persons.get(0);
        person.setMiddleName("Augusta");
        person = personRepo.save(person);
        assertEquals("Augusta", person.getMiddleName());
    }

    @Test
    public void count() {
        List<Person> persons = personRepo.findAll();
        assertTrue(persons.size() == 3);
    }

    @Test
    public void find() {
        List<Person> persons = personRepo.getByFirstnameOrLastnameLike("Peter");
        assertTrue(persons.size() == 1);
    }

    public static Person of(String firstName, String lastName, String dateStr, Gender gender, Hospital hospital) {
        DateTime dt = DateTime.parse(dateStr);
        return new Person(firstName, lastName, dt.toDate(), gender, hospital);
    }

}
