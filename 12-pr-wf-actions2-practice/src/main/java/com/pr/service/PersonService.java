package com.pr.service;

import com.pr.base.PncBuilder;
import com.pr.ents.Person;
import com.pr.model.PersonObjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by iuliana.cosmina on 8/31/15.
 */
@Service("personService")
public class PersonService {
    @Autowired
    PersonManager personManager;

    public boolean isNewPerson(PersonObjectModel person) {
        String pnc = PncBuilder.buildPnc(person.getGender(), person.getDateOfBirth(), person.getHospital().getCode());
        person.setPnc(pnc);
        Person existingPerson = personManager.getByPnc(pnc);
        return existingPerson == null;
    }

    public boolean isAdult(Person person){
        Date input = person.getDateOfBirth();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate birthday = zdt.toLocalDate();
        long yearsDelta = birthday.until(LocalDate.now(), ChronoUnit.YEARS);
        return yearsDelta>=18;
    }
}
