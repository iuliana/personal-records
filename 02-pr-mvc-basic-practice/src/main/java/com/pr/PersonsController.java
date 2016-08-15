package com.pr;

import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import com.pr.service.PersonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by iuliana.grajdeanu on 3/2/15.
 */
@Controller
@RequestMapping("/persons")
public class PersonsController {
    private Logger logger = LoggerFactory.getLogger(PersonsController.class);

    private PersonManager personManager;

    @Autowired
    public PersonsController(PersonManager personManager) {
        this.personManager = personManager;
    }

    /**
     * Handles requests to list all persons.
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Populating model with list...");
        model.addAttribute("persons", personManager.findAll());
        return "persons/list";
    }

    /**
     * Handles requests to show detail about one person.
     */
    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) throws NotFoundException {
        Person person = personManager.findById(id);

        //Decomment this to test the DataIntegrityViolationException exception handler
      /*  if (true) {
            throw new DataIntegrityViolationException("Throw test exception in order to test the global excetion handler");
        }*/

        if (person == null) {
            throw new NotFoundException(Person.class, id);
        }
        model.addAttribute("person", person);
        return "persons/show";
    }

    /**
     * Exception handler method for the PersonsController class
     * Works with default bean: SimpleMappingExceptionResolver
     * Go to URL: http://localhost:8080/mvc-basic/persons/99  to test this resolver
     *
     * @return
     */
    @ExceptionHandler
    public ModelAndView notFound(HttpServletRequest req, NotFoundException nfe) {
        logger.error("Request: " + req.getRequestURL() + " requires a non existing item.");
        ModelAndView mav = new ModelAndView();
        mav.addObject("problem", " Person not found for : " + req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }


}
