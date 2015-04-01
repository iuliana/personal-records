package com.pr;

import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import com.pr.repos.HospitalRepo;
import com.pr.repos.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iuliana.grajdeanu on 3/2/15.
 */
@Controller
@RequestMapping("/persons/{id}")
public class PersonsController {
    private Logger logger = LoggerFactory.getLogger(PersonsController.class);

    private PersonRepo personRepo;
    private HospitalRepo hospitalRepo;

    @Autowired
    public PersonsController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Autowired
    public void setHospitalRepo(HospitalRepo hospitalRepo) {
        this.hospitalRepo = hospitalRepo;
    }

    /**
     * Finds the person managed by the methods in this controller and adds it to the model
     * @param id
     *      the id of the Person instance to retrieve
     * @return
     */
    @ModelAttribute
    protected Person findPerson(@PathVariable Long id) {
        return personRepo.findOne(id);
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
        //we add the hospitalList to show in the Hospital drop-down list
        model.addAttribute(hospitalRepo.findAll());
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
            // we need to add this here as the dropdown list has to be populated correctly
            model.addAttribute(hospitalRepo.findAll());
            return "persons/edit";
        }
        personRepo.save(person);
        return "redirect:/persons/" + person.getId();
    }

}
