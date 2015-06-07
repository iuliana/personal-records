package com.pr.init;

import com.pr.base.Gender;
import com.pr.ents.Hospital;
import com.pr.ents.IdentityCard;
import com.pr.ents.Person;
import com.pr.repos.AccountRepo;
import com.pr.repos.HospitalRepo;
import com.pr.repos.PersonRepo;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by iuliana.cosmina on 3/17/15.
 */
@Service
public class DBInitializer {

    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);
    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private AccountRepo accountRepo;

    @PostConstruct
    public void init(){
        logger.info("Starting database initialization...");
        Hospital hospital = new Hospital("134181", "Constance, Romania", "General Hospital");
        hospitalRepo.save(hospital);
        List<Person> list = new ArrayList<>();
        list.add(build("John", "Smith", "1935-10-01", Gender.MALE, hospital));
        list.add(build("Peter", "Doe", "1940-11-02", Gender.MALE, hospital));
        list.add(build("Joe", "Williams", "1950-12-10", Gender.MALE, hospital));
        list.add(build("Jessica", "Jones", "1960-09-09", Gender.FEMALE, hospital));

        hospital = new Hospital("221345", "Bucharest, Romania", "\"Gh. Nica\" Clinical Hospital");
        hospitalRepo.save(hospital);
        list.add(build("Leroy", "Smith", "1970-10-08", Gender.MALE, hospital));
        list.add(build("Jane", "Doe", "1980-07-21", Gender.FEMALE, hospital));
        list.add(build("Dan", "Wayne", "1990-06-22", Gender.MALE, hospital));
        list.add(build("Dan", "Smith", "1940-11-01", Gender.MALE, hospital));


        hospital = new Hospital("112233", "Hermanstadt, Romania", "Polisano Hospital");
        hospitalRepo.save(hospital);
        list.add(build("Leroy", "Smith", "1973-10-01", Gender.MALE, hospital));
        list.add(build("Vince", "Dreyfuss", "1980-07-23", Gender.MALE, hospital));
        list.add(build("Mariah", "Wayne", "1992-06-22", Gender.FEMALE, hospital));
        list.add(build("Dan", "Smith", "1943-11-01", Gender.MALE, hospital));

        hospital = new Hospital("324567", "Iassy, Romania", "\"Lady Helena\" General Hospital");
        hospitalRepo.save(hospital);
        list.add(build("Leroy", "Smith", "1972-02-08", Gender.MALE, hospital));
        list.add(build("Mimi", "Rogers", "1983-07-20", Gender.FEMALE, hospital));
        list.add(build("Jessica", "Rabbit", "1991-07-22", Gender.FEMALE, hospital));
        list.add(build("Dan", "Smith", "1947-11-01", Gender.MALE, hospital));
        personRepo.save(list);
        logger.info("Database initialization finished.");
    }

    /**
     * Method used to build a Person instance
     * @param fn
     * @param ln
     * @param dateStr
     * @param gender
     * @param hospital
     * @return
     */
    private Person build(String fn, String ln, String dateStr, Gender gender, Hospital hospital){
        DateTime dob = DateTime.parse(dateStr);
        Person person = new Person(fn, ln, dob.toDate(), gender, hospital);
        DateTime emmitedAt = dob.plusYears(14);
        DateTime expiresAt = dob.plusYears(50);
        IdentityCard ic = new IdentityCard(person, hospital.getLocation().substring(0,2).toUpperCase(),getRandomSeries(),
                emmitedAt.toDate(), expiresAt.toDate());
        ic.setAddress(UUID.randomUUID().toString());
        person.setIdentityCard(ic);
        return person;
    }

    /**
     * Method that generates a 6 digit sequence
     * @return
     */
    private String getRandomSeries() {
        return "" + ((int) Math.floor(Math.random() * (999999 - 100000 + 1)) + 100000);
    }

}
