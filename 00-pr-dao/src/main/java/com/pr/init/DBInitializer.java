package com.pr.init;

import com.pr.base.Gender;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import com.pr.repos.AccountRepo;
import com.pr.repos.HospitalRepo;
import com.pr.repos.PersonRepo;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iuliana.cosmina on 3/17/15.
 */
@Component
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
        Hospital hospital = new Hospital("134181", "Constance", "General Hospital");
        hospitalRepo.save(hospital);
        List<Person> list = new ArrayList<>();
        list.add(new Person("John", "Smith", DateTime.parse("1935-10-01").toDate(), Gender.MALE, hospital));
        list.add(new Person("Peter", "Doe", DateTime.parse("1940-11-02").toDate(), Gender.MALE, hospital));
        list.add(new Person("Joe", "Williams", DateTime.parse("1950-12-10").toDate(), Gender.MALE, hospital));
        list.add(new Person("Jessica", "Jones", DateTime.parse("1960-09-09").toDate(), Gender.FEMALE, hospital));

        hospital = new Hospital("221345", "Bucharest", "\"Gh. Nica\" Clinical Hospital");
        hospitalRepo.save(hospital);
        list.add(new Person("Leroy", "Smith", DateTime.parse("1970-10-08").toDate(), Gender.MALE, hospital));
        list.add(new Person("Jane", "Doe", DateTime.parse("1980-07-21").toDate(), Gender.FEMALE, hospital));
        list.add(new Person("Dan", "Wayne", DateTime.parse("1990-06-22").toDate(), Gender.MALE, hospital));
        list.add(new Person("Dan", "Smith", DateTime.parse("1940-11-01").toDate(), Gender.MALE, hospital));


        hospital = new Hospital("112233", "Hermanstadt", "Polisano Hospital");
        hospitalRepo.save(hospital);
        list.add(new Person("Leroy", "Smith", DateTime.parse("1973-10-01").toDate(), Gender.MALE, hospital));
        list.add(new Person("Vince", "Dreyfuss", DateTime.parse("1980-07-23").toDate(), Gender.MALE, hospital));
        list.add(new Person("Mariah", "Wayne", DateTime.parse("1992-06-22").toDate(), Gender.FEMALE, hospital));
        list.add(new Person("Dan", "Smith", DateTime.parse("1943-11-01").toDate(), Gender.MALE, hospital));

        hospital = new Hospital("324567", "Iassy", "\"Lady Helena\" General Hospital");
        hospitalRepo.save(hospital);
        list.add(new Person("Leroy", "Smith", DateTime.parse("1972-02-08").toDate(), Gender.MALE, hospital));
        list.add(new Person("Mimi", "Rogers", DateTime.parse("1983-07-20").toDate(), Gender.FEMALE, hospital));
        list.add(new Person("Jessica", "Rabbit", DateTime.parse("1991-07-22").toDate(), Gender.FEMALE, hospital));
        list.add(new Person("Dan", "Smith", DateTime.parse("1947-11-01").toDate(), Gender.MALE, hospital));
        personRepo.save(list);
        logger.info("Database initialization finished.");
    }

}
