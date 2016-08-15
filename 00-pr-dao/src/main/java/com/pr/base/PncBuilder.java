package com.pr.base;

import com.pr.ents.Person;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Personal numerical code builder. Uses th values of the fields of a Person instance to compute a valid PNC.
 *
 * @author Iuliana Cosmina
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

    public static String buildPnc(Gender gender, Date dob, String hospitalCode) {
        StringBuilder pncBuilder = new StringBuilder();
        pncBuilder.append(gender.getCode());
        pncBuilder.append(pcFormatter.format(dob));
        pncBuilder.append(hospitalCode);
        return pncBuilder.toString();
    }

}
