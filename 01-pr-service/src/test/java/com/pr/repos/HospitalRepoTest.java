package com.pr.repos;

import com.pr.ents.Hospital;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/app-dao-config.xml", "classpath:spring/db-config.xml"})
@Transactional
public class HospitalRepoTest {
    @Autowired
    private HospitalRepo hospitalRepo;

    @Before
    public void init() {
        assertNotNull(hospitalRepo);
        hospitalRepo.save(of("121412", "Maryland", "Maryland Central Hospital"));
        hospitalRepo.save(of("121413", "Portland", "Portland Central Hospital"));
        hospitalRepo.save(of("131415", "Louisiana", "Louisiana Main Hospital"));
    }
    
    @Test
    public void update(){
        Hospital hospital = hospitalRepo.findByCode("121412");
        hospital.setAddress("new address");
        hospital = hospitalRepo.save(hospital);
        assertEquals("new address", hospital.getAddress());
    }

    @Test
    public void count() {
        List<Hospital> hospitals = hospitalRepo.findAll();
        assertTrue( hospitals.size() == 3);
    }

    @Test
    public void findByCode() {
        Hospital hospital = hospitalRepo.findByCode("131415");
        assertNotNull(hospital);
    }

    @Test
    public void findByCodeLike() {
        List<Hospital> hospitals = hospitalRepo.findByCodeLike("12");
        assertTrue(hospitals.size() == 2);
    }

    public static Hospital of(String code, String location, String name) {
        return new Hospital(code, location, name);
    }
}
