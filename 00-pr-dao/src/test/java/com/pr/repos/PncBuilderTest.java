package com.pr.repos;

import com.pr.base.Gender;
import com.pr.base.PncBuilder;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;

/**
 * @author Iuliana Cosmina
 */
public class PncBuilderTest {

    private Logger logger = LoggerFactory.getLogger(PncBuilderTest.class);

    @Test
    public void test() throws ParseException {
        DateTime dt = DateTime.parse("1970-11-12");
        Hospital hospital = new Hospital("121412", "Maryland", "Maryland Central");
        Person person = new Person("Julie", "Andrews", dt.toDate(), Gender.FEMALE, hospital);
        String expectedPnc = "2701112121412";
        String producedPnc = PncBuilder.build(person);
        assertEquals(expectedPnc, producedPnc);
        logger.info(person.toString());
    }

}
