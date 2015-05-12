package com.pr;

import com.pr.ents.Hospital;
import com.pr.ents.Person;
import com.pr.repos.HospitalRepo;
import com.pr.repos.PersonRepo;
import com.pr.service.HospitalManager;
import com.pr.service.PersonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by iuliana.grajdeanu on 3/2/15.
 */
@Controller
@RequestMapping("/persons/{id}")
public class PersonsController {
    private Logger logger = LoggerFactory.getLogger(PersonsController.class);

    private PersonManager personManager;
    private HospitalManager hospitalManager;

    @Autowired
    public PersonsController(PersonManager personManager) {
        this.personManager = personManager;
    }

    @Autowired
    public void setHospitalManager(HospitalManager hospitalManager) {
        this.hospitalManager = hospitalManager;
    }

    /**
     * Finds the person managed by the methods in this controller and adds it to the model
     * @param id
     *      the id of the Person instance to retrieve
     * @return
     */
    //TODO 10. Annotate this method properly in order for it to be execute before every request being handled by this controller
    protected Person findPerson(@PathVariable Long id) {
        return personManager.findById(id);
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
    /* TODO 11. Annotate this method properly in order to properly handle the submission request
     coming from the form in the persons/edit.jsp page */
    // TODO 12. Enable validation for the form object
    public String save(Person person, BindingResult result, Model model) {
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
