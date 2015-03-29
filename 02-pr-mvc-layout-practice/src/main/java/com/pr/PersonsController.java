package com.pr;

import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import com.pr.repos.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iuliana.grajdeanu on 3/2/15.
 */
@Controller
@RequestMapping("/persons")
public class PersonsController {
    private Logger logger = LoggerFactory.getLogger(PersonsController.class);

    private PersonRepo personRepo;

    @Autowired
    public PersonsController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    /**
     * Handles requests to list all persons.
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Populating model with list...");
        model.addAttribute("persons", personRepo.findAll());
        return "persons/list";
    }

    /**
     * Method which is called via "redirect:" from the HospitalController
     * @param persons
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String listh(@ModelAttribute("persons") ArrayList<Person> persons) {
        return "persons/list";
    }

    /**
     * Handles requests to shows details about one person.
     */
    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) throws NotFoundException {
        Person person = personRepo.findOne(id);
        if(person == null) {
            throw new NotFoundException(Person.class, id);
        }
        model.addAttribute("person", person);
        return "persons/show";
    }

    /**
     * Handles requests to shows details about one person.
     */
    //TODO 6. Implement the show method using @RequestParam
}
