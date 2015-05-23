package com.pr.rest;

import com.pr.BaseController;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    @RequestMapping(value = "id/{id}", method = RequestMethod.GET, produces = "application/json")
    public Person getPersonById(@PathVariable Long id) {
        logger.info("-----> PERSON: " + id);
        return personManager.findById(id);
    }

    /**
     * Provide the details of a person with the given id.
     */
    @RequestMapping(value = "pnc/{pnc}", method = RequestMethod.GET, produces = "application/json")
    public Person getPersonByPnc(@PathVariable String pnc) {
        logger.info("-----> PERSON: " + pnc);
        return personManager.getByPnc(pnc);
    }

    /**
     * Provide a list with all persons
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Person> getAll() {
        logger.info("-----> ALL ");
        return personManager.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = {"Accept=*/*", "Content-Type=application/json"})
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
    public void deletePerson(@PathVariable String pnc) {
        Person person = personManager.getByPnc(pnc);
        if (person != null) {
            personManager.delete(person);
        }
    }
}
