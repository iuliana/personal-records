package com.pr.rest;

import com.pr.BaseController;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/21/15.
 */
@RestController
@RequestMapping(value = "/rest-persons")
public class PersonsRestController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(PersonsRestController.class);

    /**
     * Provide the details of a person with the given id.
     */
    @RequestMapping(value = "id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPersonById(@PathVariable Long id) throws NotFoundException {
        logger.info("-----> PERSON: " + id);
        Person person = personManager.findById(id);
        if (person == null) {
            throw new NotFoundException(Person.class, id.toString());
        }
        return person;
    }

    /**
     * Provide the details of a person with the given id.
     */
    @RequestMapping(value = "pnc/{pnc}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPersonByPnc(@PathVariable String pnc) throws NotFoundException {
        logger.info("-----> PERSON: " + pnc);
        Person person = personManager.getByPnc(pnc);
        if (person == null) {
            throw new NotFoundException(Person.class, pnc);
        }
        return person;
    }

    /**
     * Provide a list with all persons
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getAll() {
        logger.info("-----> ALL ");
        return personManager.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person createPerson(@RequestBody Person newPerson) {
        logger.info("-----> CREATE");
        Hospital hospital = hospitalManager.findByCode(newPerson.getHospital().getCode());
        newPerson.setHospital(hospital);
        Person person = personManager.save(newPerson);
        logger.info("-----> PERSON: " + person);
        return person;
    }

    /**
     * Determines URL of person resource based on the full URL of the given request,
     * appending the path info with the given childIdentifier using a UriTemplate.
     */
    protected static String getLocationForPersonResource(StringBuilder url, Object childIdentifier) {
        UriTemplate template = new UriTemplate(url.append("/{id}").toString());
        return template.expand(childIdentifier).toASCIIString();
    }

    /**
     * Deletes a person by pnc
     *
     * @param pnc
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/delete/{pnc}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable String pnc) throws NotFoundException {
        Person person = personManager.getByPnc(pnc);
        if (person != null) {
            personManager.delete(person);
        } else {
            throw new NotFoundException(Person.class, pnc);
        }
    }


    /**
     * Maps IllegalArgumentExceptions to a 404 Not Found HTTP status code.
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This person is not found in the system")
    @ExceptionHandler({NotFoundException.class})
    public void handleNotFound() {
        // just return empty 404
        logger.info("-----> PERSON not found.");
    }

    /**
     * Maps DataIntegrityViolationException to a 409 Conflict HTTP status code.
     */
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Another person with the same identity card exists")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void handleAlreadyExists() {
        // just return empty 409
        logger.info("-----> Database operation failure");
    }
}
