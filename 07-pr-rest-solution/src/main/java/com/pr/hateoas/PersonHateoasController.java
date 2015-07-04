package com.pr.hateoas;

import com.pr.BaseController;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@ExposesResourceFor(Person.class)
@RequestMapping("/hateoas")
public class PersonHateoasController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(PersonHateoasController.class);

    @Autowired
    EntityLinks entityLinks;

    /**
     * Provide the details of a person with the given id generated with HATEOAS
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/hal+json")
    public HttpEntity<PersonHateoas> getPersonHateoasById(@PathVariable Long id) throws NotFoundException {
        logger.info("-----> PERSON: " + id);
        Person person = personManager.findById(id);
        if (person == null) {
            throw new NotFoundException(Person.class, id.toString());
        }
        PersonHateoas ph = new PersonHateoas(person);

        ph.add(entityLinks.linkToSingleResource(Person.class, 1L));
        return new ResponseEntity<>(ph, HttpStatus.OK);
    }
}