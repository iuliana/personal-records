package com.pr.rest;

import com.pr.ents.Hospital;
import com.pr.ents.Person;
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
 * Created by iuliana.cosmina on 5/24/15.
 */
public class RestHospitalControllerTest {

    private static final String HOSPITAL_BASE_URL = "http://localhost:8080/mvc-rest/rest-hospitals/";

    private RestTemplate restTemplate = null;


    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }


    @Test
    public void listHospitals() {
        String url = HOSPITAL_BASE_URL + "all";
        // we have to use Hospital[] instead of List<Hospital>, or Jackson won't know what type to unmarshall to
        Hospital[] hospitals = restTemplate.getForObject(url, Hospital[].class);

        // Initially 4 hospitals in database, but will go up if you rerun this test multiple times
        assertTrue(hospitals.length >= 4);
    }

    /**
     * Test person retrieval by id using REST request
     */
    @Test
    public void getHospital() {
        String url = HOSPITAL_BASE_URL + "{code}";
        Hospital hospital = restTemplate.getForObject(url, Hospital.class, "134181");

        assertNotNull(hospital);
        assertEquals("General Hospital", hospital.getName());
    }

    /**
     * Test hospital creation using REST POST request
     */
    @Test
    public void createHospital() throws URISyntaxException {
        String url = HOSPITAL_BASE_URL + "create";

        Hospital hospital = new Hospital();
        hospital.setCode("123189");
        hospital.setName("New Hope Research Hospital");
        hospital.setAddress("PO NHH23");
        hospital.setLocation("Tomorrowland");

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("custom", true + "");

        final HttpEntity<Hospital> hospitalRequest = new HttpEntity<>(hospital, headers);
        Hospital newHospital = this.restTemplate.postForObject(url, hospitalRequest, Hospital.class);

        assertNotNull(newHospital);
        assertEquals(newHospital.getName(), hospital.getName());
        assertEquals(newHospital.getCode(), hospital.getCode());
    }


    /**
     * Negative test: test non existent hospital retrieval by id using REST request
     */
    @Test(expected = HttpClientErrorException.class)
    public void getNotExistingHospital() {
        String url = HOSPITAL_BASE_URL + "{code}";
        Hospital hospital = restTemplate.getForObject(url, Hospital.class, "113355");

        assertNotNull(hospital);
    }

    /**
     * Negative test: test to create duplicate person
     */
    @Test(expected = HttpClientErrorException.class)
    public void duplicate() {
        String url = HOSPITAL_BASE_URL + "create";

        Hospital hospital = new Hospital();
        hospital.setCode("123188");
        hospital.setName("New Hope Research Hospital");
        hospital.setAddress("sample address");
        hospital.setLocation("Tomorrowland");

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("custom", true + "");

        final HttpEntity<Hospital> hospitalRequest = new HttpEntity<>(hospital, headers);
        this.restTemplate.postForObject(url, hospitalRequest, Hospital.class);
        this.restTemplate.postForObject(url, hospitalRequest, Hospital.class);
    }


    /**
     * Test person deletion by Hospital Code.
     * This method deletes the Hospital instance crested by the createHospital test method.
     */
    @Test(expected = HttpClientErrorException.class)
    public void deleteHospital() {
        String url = HOSPITAL_BASE_URL + "delete/123189";
        restTemplate.delete(url);

        url = HOSPITAL_BASE_URL + "{code}";
        Hospital hospital = restTemplate.getForObject(url, Hospital.class, "123189");
        assertNull(hospital);
    }

}
