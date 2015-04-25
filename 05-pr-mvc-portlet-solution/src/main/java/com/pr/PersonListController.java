package com.pr;

import com.pr.ents.Person;
import com.pr.repos.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.RenderResponse;
import java.util.List;

/**
 * Created by iuliana.cosmina on 4/19/15.
 */
@Controller("personList")
@RequestMapping("VIEW")
public class PersonListController {

    private Logger logger = LoggerFactory.getLogger(PersonListController.class);

    private PersonRepo personRepo;

    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public PersonListController() {
        // required by Liferay
    }

    @RenderMapping
    public String showPersons() {
        return "list";
    }

    @ModelAttribute(value="persons")
    public List<Person> getPersons() {
        return personRepo.findAll();
    }
}
