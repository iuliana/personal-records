package com.pr.rest;

import com.pr.ents.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 5/23/15.
 */
public class AsyncConsumerTest {

    private RestTemplate restTemplate = null;
    private static final String PERSON_BASE_URL = "http://localhost:8080/mvc-rest/rest-persons/";

    @Before
    public void setUp() {
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
    public void asyncGet() throws Exception {
        Future<Person> personFuture = findPerson();
        Person person = personFuture.get();

        assertNotNull(person);
        assertEquals("John", person.getFirstName());
        assertEquals("Smith", person.getLastName());
    }

    @Async
    public Future<Person> findPerson() throws InterruptedException {
        String url = PERSON_BASE_URL + "id/1";
        Person person = restTemplate.getForObject(url, Person.class);
        Thread.sleep(1000L);
        return new AsyncResult<>(person);
    }
}
