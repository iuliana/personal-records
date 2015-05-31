package com.pr.rest;

import com.pr.ents.Person;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 5/23/15.
 */
public class AsyncRestTemplateTest {
    private Logger logger = LoggerFactory.getLogger(AsyncRestTemplateTest.class);
    /**
     * Rest Services endpoints
     */
    private static final String PERSON_BASE_URL = "http://localhost:8080/mvc-rest/rest-persons/";

    AsyncRestTemplate asyncRestTemplate = null;

    @Before
    public void setUp() {
        asyncRestTemplate = new AsyncRestTemplate();
    }

    @Test
    public void findPerson() throws InterruptedException, ExecutionException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", requestHeaders);

        String url = PERSON_BASE_URL + "id/{id}";
        Future<ResponseEntity<Person>> futurePerson = asyncRestTemplate.exchange(url, HttpMethod.GET, entity, Person.class, "5");

        //waiting a little, to give time to the async call to complete
        Thread.sleep(1000L);

        ResponseEntity<Person> result = futurePerson.get();
        Person person = result.getBody();
        logger.info("------> Response received= " + person);
        assertNotNull(person);
        assertEquals("Leroy", person.getFirstName());
        assertEquals("Smith", person.getLastName());
    }

    @Test
    public void testGetUserAsyncWithCallback() throws InterruptedException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", requestHeaders);

        String url = PERSON_BASE_URL + "id/{id}";
        ListenableFuture<ResponseEntity<Person>> futurePerson = asyncRestTemplate.exchange(url, HttpMethod.GET, entity, Person.class, "5");

        futurePerson.addCallback(new ListenableFutureCallback<ResponseEntity<Person>>() {
            @Override
            public void onSuccess(ResponseEntity result) {
                Person person = (Person) result.getBody();
                logger.info("------> Response received (async callable): " + person);
                assertNotNull(person);
                assertEquals("Leroy", person.getFirstName());
                assertEquals("Smith", person.getLastName());
            }

            @Override
            public void onFailure(Throwable t) {
                logger.error("------> Async call failure!", t);
            }
        });

        //waiting a little, to give time to the async call to complete
        Thread.sleep(1000L);

    }
}
