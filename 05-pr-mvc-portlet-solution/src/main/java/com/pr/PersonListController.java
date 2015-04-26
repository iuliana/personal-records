package com.pr;

import com.pr.ents.Person;
import com.pr.repos.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PersonRepo personRepo;

    @RenderMapping
    public String render() {
        return "list";
    }

    @ModelAttribute(value="persons")
    public List<Person> getPersons() {
        return personRepo.findAll();
    }
}
