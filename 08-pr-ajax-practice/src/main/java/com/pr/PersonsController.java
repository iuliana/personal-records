package com.pr;

import com.pr.ents.Hospital;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iuliana.grajdeanu on 3/2/15.
 */
@Controller
@RequestMapping("/persons/{pnc}")
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
     * @param pnc
     *      the id of the Person instance to retrieve
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Person findPerson(@PathVariable String pnc) throws NotFoundException {
        Person person = personManager.getByPnc(pnc);
        if(person == null) {
            throw new NotFoundException(Person.class, null);
        }
        return person;
    }

}
