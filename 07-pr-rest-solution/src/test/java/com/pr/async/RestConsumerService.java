package com.pr.async;

import com.pr.ents.Person;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by iuliana.cosmina on 5/23/15.
 */
@Service
public class RestConsumerService {

    RestTemplate restTemplate = new RestTemplate();

    @Async
    public Future<Person> findPerson(Long id) throws InterruptedException {
        String url = "http://localhost:8080/mvc-rest/rest-persons/id/" + id;
        Person person = restTemplate.getForObject(url, Person.class);
        Thread.sleep(1000L);
        return new AsyncResult<>(person);
    }
}
