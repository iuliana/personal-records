package com.pr.hateoas;

import com.pr.ents.Person;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 5/29/15.
 */

public class PersonHateoasControllerTest {

    @Test
    public void getHateoasPerson() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/mvc-rest/hateoas/{id}";

        PersonHateoas personHateoas  = restTemplate.execute(url, HttpMethod.GET, request -> {
            HttpHeaders headers = request.getHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            System.out.println("Request headers = " + headers);
        }, new HttpMessageConverterExtractor<>(PersonHateoas.class, restTemplate.getMessageConverters())
                , new HashMap<String, Object>() {{
            put("id", "1");
        }});

        assertNotNull(personHateoas);
        assertTrue(personHateoas.hasLinks());
        assertEquals("http://localhost:8080/mvc-rest/hateoas/1" ,personHateoas.getId().getHref());
        assertEquals("John", personHateoas.getPerson().getFirstName());
        assertEquals("Smith", personHateoas.getPerson().getLastName());
    }

}
