package com.pr.rest;

import com.pr.base.Gender;
import com.pr.ents.Hospital;
import com.pr.ents.IdentityCard;
import com.pr.ents.Person;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

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
    public void setUp() {
        restTemplate = new RestTemplate();
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
        Person person = buildPerson();

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
     * Negative test: test non existent person retrieval by id using REST request
     */
    @Test(expected = HttpClientErrorException.class)
    public void getNotExistingPerson() {
        String url = PERSON_BASE_URL + "id/{id}";
        Person person = restTemplate.getForObject(url, Person.class, "99");

        assertNotNull(person);
    }

    /**
     * Negative test: test to create duplicate person
     */
    @Test(expected = HttpClientErrorException.class)
    public void duplicate() {
        String url = PERSON_BASE_URL + "create";
        Person person = buildPerson();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("custom", true + "");

        final HttpEntity<Person> wineRequest = new HttpEntity<>(person, headers);
        this.restTemplate.postForObject(url, wineRequest, Person.class);
        this.restTemplate.postForObject(url, wineRequest, Person.class);
    }


    /**
     * Test person deletion by Personal Numerical Code.
     * This method deletes the Person instance crested by the createPerson test method.
     */
    @Test
    public void deletePerson() {
        String url = PERSON_BASE_URL + "delete/1760413324567";
        restTemplate.delete(url);

        url = PERSON_BASE_URL + "pnc/{pnc}";
        Person person = restTemplate.getForObject(url, Person.class, "1760413324567");
        assertNull(person);
    }

    /**
     * Method that retrieves Hospital instance with code "324567"
     *
     * @return
     */
    private Hospital getHospital() {
        String url = HOSPITAL_BASE_URL + "{code}";
        Hospital hospital = restTemplate.getForObject(url, Hospital.class, "324567");
        assertNotNull(hospital);
        return hospital;
    }

    /**
     * Utility method used to create a person
     *
     * @return
     */
    private Person buildPerson() {
        Hospital hospital = getHospital();
        assertNotNull(hospital);

        Person person = new Person();
        person.setFirstName("Jonathan");
        person.setLastName("Brandis");
        person.setDateOfBirth(DateTime.parse("1976-04-13").toDate());
        person.setGender(Gender.MALE);
        hospital.addPerson(person);
        IdentityCard ic = new IdentityCard(person, "MY", "125223", DateTime.parse("1986-04-13").toDate(),
                DateTime.parse("1996-04-13").toDate());
        ic.setAddress("-");
        person.setIdentityCard(ic);
        return person;
    }

}
