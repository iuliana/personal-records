package com.pr.repos;

import com.pr.base.Gender;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/app-dao-config.xml", "classpath:spring/db-config.xml"})
@Transactional
@TransactionConfiguration
public class AccountRepoTest {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private HospitalRepo hospitalRepo;
    
    @Before
    public void init(){
        assertNotNull(hospitalRepo);
        Hospital hospital = HospitalRepoTest.of("121415", "California", "California Central Hospital");
        hospital = hospitalRepo.save(hospital);

        assertNotNull(personRepo);
        Person person = personRepo.save(PersonRepoTest.of("Barbara", "Liskov", "1939-11-07", Gender.FEMALE, hospital));
    }
    
    @Test
    public void find(){
        
        
    }
    
}
