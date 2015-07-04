package com.pr.hateoas;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pr.ents.Person;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 5/29/15.
 */

public class PersonHateoasControllerTest {

    @Test
    public void getHateoasPerson() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
        converter.setObjectMapper(mapper);

        RestTemplate restTemplate = new RestTemplate(Collections.<HttpMessageConverter<?>> singletonList(converter));

        String url = "http://localhost:8080/mvc-rest/hateoas/{id}";

        ResponseEntity<PersonHateoas> responseEntity =
                restTemplate.getForEntity(url, PersonHateoas.class, "1");
        PersonHateoas personHateoas = responseEntity.getBody();

        assertNotNull(personHateoas);
        assertTrue(personHateoas.hasLinks());
        assertEquals("http://localhost:8080/mvc-rest/hateoas/1", personHateoas.getLink("self").getHref());
        assertEquals("John", personHateoas.getPerson().getFirstName());
        assertEquals("Smith", personHateoas.getPerson().getLastName());
    }

}