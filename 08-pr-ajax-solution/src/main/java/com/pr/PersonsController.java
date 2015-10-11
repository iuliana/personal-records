package com.pr;

import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import com.pr.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by iuliana.grajdeanu on 3/2/15.
 */
@Controller
@RequestMapping("/persons/{pnc}")
public class PersonsController {
    private PersonManager personManager;

    @Autowired
    public PersonsController(PersonManager personManager) {
        this.personManager = personManager;
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
