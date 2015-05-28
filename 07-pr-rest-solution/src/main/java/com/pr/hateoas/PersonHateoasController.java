package com.pr.hateoas;

import com.pr.BaseController;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by iuliana.cosmina on 5/29/15.
 * Description: Controller used to manage HATEOAS Person representations
 */
@RestController
@RequestMapping(value = "/hateoas")
public class PersonHateoasController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(PersonHateoasController.class);

    /**
     * Provide the details of a person with the given id generated with HATEOAS
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PersonHateoas> getPersonHateoasById(@PathVariable Long id) throws NotFoundException {
        logger.info("-----> PERSON: " + id);
        Person person = personManager.findById(id);
        if (person == null) {
            throw new NotFoundException(Person.class, id.toString());
        }
        PersonHateoas ph = new PersonHateoas(person);
        ph.add(linkTo(methodOn(PersonHateoasController.class).getPersonHateoasById(person.getId())).withSelfRel());
        return new ResponseEntity<>(ph, HttpStatus.OK);
    }

}
