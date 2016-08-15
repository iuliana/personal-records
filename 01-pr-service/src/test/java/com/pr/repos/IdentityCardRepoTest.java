package com.pr.repos;

import com.pr.base.Gender;
import com.pr.ents.Hospital;
import com.pr.ents.IdentityCard;
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
public class IdentityCardRepoTest extends RepoTestCase {
    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private IdentityCardRepo identityCardRepo;

    @Before
    public void init(){
        assertNotNull(hospitalRepo);
        Hospital hospital = HospitalRepoTest.of("121415", "California", "California Central Hospital");
        hospital = hospitalRepo.save(hospital);

        assertNotNull(personRepo);
        Person person = personRepo.save(PersonRepoTest.of("Barbara", "Liskov", "1939-11-07", Gender.FEMALE, hospital));
        DateTime dt1 = DateTime.parse("1953-11-05");
        DateTime dt2 = DateTime.parse("1963-11-05");
        IdentityCard identityCard = new IdentityCard(person, "BL", "187313", dt1.toDate(), dt2.toDate());
        person.setIdentityCard(identityCard);
        personRepo.save(person);

        hospital = HospitalRepoTest.of("121417", "Oxford", "Oxford Central Hospital");
        hospital = hospitalRepo.save(hospital);
        person = personRepo.save(PersonRepoTest.of("Stephen", "Hawking", "1942-01-08", Gender.MALE, hospital));
        dt1 = DateTime.parse("1956-01-05");
        dt2 = DateTime.parse("1966-11-05");
        identityCard = new IdentityCard(person, "SH", "189313", dt1.toDate(), dt2.toDate());
        person.setIdentityCard(identityCard);
        personRepo.saveAndFlush(person);

        assertNotNull(identityCardRepo);
    }
    
    @Test
    public void count(){
        List<IdentityCard> ids = identityCardRepo.findAll();
        assertTrue(ids.size() == 2);
    }
    
    @Test
     public void find(){
        IdentityCard ic =  identityCardRepo.findByPnc("1420108121417");
        assertNotNull(ic);
        Person person = ic.getPerson();
        assertEquals("Hawking", person.getLastName());
    }
    
}
