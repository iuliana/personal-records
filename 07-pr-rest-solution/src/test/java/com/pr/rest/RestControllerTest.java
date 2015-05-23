package com.pr.rest;

import com.pr.base.Gender;
import com.pr.ents.Hospital;
import com.pr.ents.IdentityCard;
import com.pr.ents.Person;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 5/21/15.
 * Description: This class tests the REST methods
 */
public class RestControllerTest {
    /**
     * Rest Services endpoints
     */
    private static final String PERSON_BASE_URL = "http://localhost:8080/mvc-rest/rest-persons/";
    private static final String HOSPITAL_BASE_URL = "http://localhost:8080/mvc-rest/rest-hospitals/";

    private RestTemplate restTemplate = null;


    @Before
    public void setUp(){
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        supportedMediaTypes.add(mediaType);
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        jacksonConverter.setSupportedMediaTypes(supportedMediaTypes);
        messageConverters.add(jacksonConverter);
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
    }

    @Test
    public void listPersons() {
        String url = PERSON_BASE_URL + "all";
        // we have to use Persons[] instead of List<Persons>, or Jackson won't know what type to unmarshall to
        Person[] persons = restTemplate.getForObject(url, Person[].class);

        // Initially 16 persons in database, but will go up if you rerun this test multiple times
        assertTrue(persons.length >= 16);
    }

    /**
     * Test person creation using REST POST request
     */
    @Test
    public void createPerson() throws URISyntaxException {
        String url = PERSON_BASE_URL + "create";
        Hospital hospital = getHospital();
        assertNotNull(hospital);

        Person person = new Person();
        person.setFirstName("Jonathan");
        person.setLastName("Brandis");
        person.setDateOfBirth(DateTime.parse("1976-04-13").toDate());
        person.setGender(Gender.MALE);
        hospital.addPerson(person);
        IdentityCard ic = new IdentityCard(person,"MY", "125223", DateTime.parse("1986-04-13").toDate(),
                DateTime.parse("1996-04-13").toDate());
        ic.setAddress("-");
        person.setIdentityCard(ic);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("custom", true + "");

        final HttpEntity<Person> wineRequest = new HttpEntity<>(person, headers);
        Person newPerson = this.restTemplate.postForObject(url, wineRequest,
                Person.class);
        assertNotNull(newPerson);
        assertEquals(newPerson.getFirstName(), person.getFirstName());
        assertEquals(newPerson.getLastName(), person.getLastName());
        assertEquals(newPerson.getIdentityCard().getPnc(), person.getIdentityCard().getPnc());
    }

    /**
     * Test person retrieval by id using REST request
     */
    @Test
    public void getPerson() {
        String url = PERSON_BASE_URL + "id/{id}";
        Person person = restTemplate.getForObject(url, Person.class, "1");

        assertNotNull(person);
        assertEquals("John", person.getFirstName());
        assertEquals("Smith", person.getLastName());
    }


    /**
     * Test person deletion by Personal Numerical Code.
     * This method deletes the Person instance crested by the createPerson test method.
     */
    @Test
    public void deletePerson(){
        String url = PERSON_BASE_URL + "delete/1760413324567";
        restTemplate.delete(url);

        url = PERSON_BASE_URL + "pnc/{pnc}";
        Person person = restTemplate.getForObject(url, Person.class, "1760413324567");
        assertNull(person);
    }

    /**
     * Method that retrieves Hospital instance with code "324567"
     * @return
     */
    private Hospital getHospital() {
        String url = HOSPITAL_BASE_URL + "{code}";
        Hospital hospital = restTemplate.getForObject(url, Hospital.class, "324567");
        assertNotNull(hospital);
        return hospital;
    }


}
