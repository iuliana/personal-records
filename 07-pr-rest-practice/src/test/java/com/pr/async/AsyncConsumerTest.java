package com.pr.async;

import com.pr.async.AppConfig;
import com.pr.async.RestConsumerService;
import com.pr.config.WebConfig;
import com.pr.ents.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 5/23/15.
 */
@EnableAsync
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AsyncConsumerTest {

   @Autowired
   RestConsumerService service;

    @Before
    public void setUp(){
        assertNotNull(service);
    }

    @Test
    public void asyncOp() throws InterruptedException, ExecutionException {
        Future<Person> personFuture1 = service.findPerson(1L);
        Future<Person> personFuture2 = service.findPerson(2L);
        Future<Person> personFuture3 = service.findPerson(3L);

        //wait until requests are done
        while (!(personFuture1.isDone() && personFuture2.isDone() && personFuture3.isDone())) {
            Thread.sleep(10); //10-millisecond pause between each check
        }

        assertNotNull(personFuture1.get());
        assertNotNull(personFuture2.get());
        assertNotNull(personFuture3.get());
    }

}
