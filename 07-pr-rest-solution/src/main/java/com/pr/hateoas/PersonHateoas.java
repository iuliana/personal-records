package com.pr.hateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pr.ents.Person;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by iuliana.cosmina on 5/29/15.
 * Description: Class that wraps around Person in order to provide HATEOAS specific properties
 */
public class PersonHateoas extends ResourceSupport {

    private Person person;

    @JsonCreator
    public PersonHateoas(@JsonProperty("person") Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
