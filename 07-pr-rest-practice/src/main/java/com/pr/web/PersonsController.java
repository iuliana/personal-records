package com.pr.web;

import com.pr.BaseController;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import com.pr.service.HospitalManager;
import com.pr.service.PersonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by iuliana.grajdeanu on 3/2/15.
 */
@Controller
@RequestMapping("/persons/{id}")
public class PersonsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(PersonsController.class);

    /**
     * Finds the person managed by the methods in this controller and adds it to the model
     * @param id
     *      the id of the Person instance to retrieve
     * @return
     */
    @ModelAttribute
    protected Person findPerson(@PathVariable Long id) throws NotFoundException {
        Person person = personManager.findById(id);
        if(person == null) {
            throw new NotFoundException(Person.class, id.toString());
        }
        return person;
    }

    /**
     * Handler method to show details of a person in non editable mode
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        return "persons/show";
    }

    /**
     * Handler method to show a person details in edit mode
     * @return
     */
    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String edit(Model model) {
        return "persons/edit";
    }

    /**
     * A handler method for applying changes to the a Person instance
     * @param person
     *      the Person instance to alter
     * @param result
     *      the object containing validation results if any
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "persons/edit";
        }
        personManager.save(person);
        return "redirect:/persons/" + person.getId();
    }


    @ModelAttribute
    private List<Hospital> getHospitals() {
        return hospitalManager.findAll();
    }

}
