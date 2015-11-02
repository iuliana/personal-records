package com.pr.problem;

import com.pr.ents.Person;

/**
 * Created by iuliana.cosmina on 9/1/15.
 */
public class DuplicatePersonException extends RuntimeException {

    private Person person;

    public DuplicatePersonException(Person person) {
        super("The person already exists in the system.");
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
