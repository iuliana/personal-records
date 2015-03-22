package com.pr;

import com.pr.repos.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Populating model with list...");
        model.addAttribute("persons", personRepo.findAll());
        return "persons/list";
    }

    /**
     * Handles requests to shows detail about one person.
     */
    @RequestMapping(value="/{number}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("person", personRepo.findOne(id));
        return "persons/show";
    }
}
