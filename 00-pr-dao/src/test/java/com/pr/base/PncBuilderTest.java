package com.pr.base;

import com.pr.ents.Hospital;
import com.pr.ents.Person;
import org.joda.time.DateTime;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
public class PncBuilderTest {
    
    @Test
    public void test() throws ParseException {
        DateTime dt = DateTime.parse("1970-11-12");
        Hospital hospital = new Hospital("121412", "Maryland", "Maryland Central");
        Person person = new Person("Julie", "Andrews", dt.toDate(), Gender.FEMALE,  hospital);
        String expectedPnc = "2701112121412";
        String producedPnc = PncBuilder.build(person);
        assertEquals(expectedPnc, producedPnc);
        System.out.println(person);
    }
    
}
