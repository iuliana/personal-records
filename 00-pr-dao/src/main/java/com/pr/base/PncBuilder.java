package com.pr.base;

import com.pr.ents.Person;

import java.text.SimpleDateFormat;

/**
 * Personal numerical code builder. Uses th values of the fields of a Person instance to compute a valid PNC.
 * Created by iuliana.cosmina on 12/28/14.
 */
public class PncBuilder {

    private static final SimpleDateFormat pcFormatter = new SimpleDateFormat("YYMMdd");

    public static String build(Person person) {
        StringBuilder pncBuilder = new StringBuilder();
        pncBuilder.append(person.getGender().getCode());
        pncBuilder.append(pcFormatter.format(person.getDateOfBirth()));
        pncBuilder.append(person.getHospital().getCode());
        return pncBuilder.toString();
    }
}
