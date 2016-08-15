package com.pr.persons;

import com.pr.base.PncBuilder;
import com.pr.ents.IdentityCard;
import com.pr.ents.Person;
import com.pr.model.PersonObjectModel;
import com.pr.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 8/19/15.
 */
@Component("personBuilder")
public class PersonBuilder {
    @Autowired
    PersonManager personManager;

    public PersonObjectModel buildPersonObject(Person person){
        PersonObjectModel personObjectModel = new PersonObjectModel();
        personObjectModel.setFirstName(person.getFirstName());
        personObjectModel.setMiddleName(person.getMiddleName());
        personObjectModel.setLastName(person.getLastName());
        personObjectModel.setGender(person.getGender());
        personObjectModel.setDateOfBirth(person.getDateOfBirth());
        personObjectModel.setHospital(person.getHospital());
        personObjectModel.setPnc(PncBuilder.build(person));
        return personObjectModel;
    }

    public Person savePersonEntity(PersonObjectModel personModel, IdentityCard ic){
        Person newPerson = new Person(personModel.getFirstName(), personModel.getLastName(),
                personModel.getDateOfBirth(),personModel.getGender(), personModel.getHospital());
        newPerson.setMiddleName(personModel.getMiddleName());
        newPerson.setIdentityCard(ic);
        newPerson = personManager.save(newPerson);
        return newPerson;
    }
}

