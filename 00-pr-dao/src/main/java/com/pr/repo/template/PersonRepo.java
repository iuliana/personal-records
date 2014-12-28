package com.pr.repo.template;

import com.pr.ents.Person;

import java.util.Date;
import java.util.List;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
public interface PersonRepo {
    Person findById(Integer id);
    
    List<Person>  findByName(String name);
    
    List<Person> findByDob(Date dob);
    
    void save(Person person);
}
