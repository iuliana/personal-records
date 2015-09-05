package com.pr.web;

import com.pr.BaseController;
import com.pr.dto.CriteriaDto;
import com.pr.ents.Person;
import com.pr.problem.InvalidCriteriaException;
import com.pr.service.PersonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by iuliana.cosmina on 3/30/15.
 */
@Controller
@RequestMapping(value = "/persons")
public class PersonsListController extends BaseController {

    @Autowired
    private MessageSource messageSource;

    private Logger logger = LoggerFactory.getLogger(PersonsListController.class);

    /**
     * Handles requests to list all persons.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("persons", personManager.findAll());
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
     * Displays a person search form to the user
     *
     * @param criteria
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(CriteriaDto criteria) {
        return "persons/search";
    }


    @RequestMapping(value = "/go", method = RequestMethod.GET)
    public String processSubmit(@Valid CriteriaDto criteria, BindingResult result, Model model, Locale locale) {
        if (result.hasErrors()) {
            return "persons/search";
        }
        try {
            List<Person> persons = personManager.getByCriteriaDto(criteria);

            if (persons.size() == 0) {
                result.addError(new FieldError("criteriaDto", "noResults", "No result matching the criteria."));
                return "persons/search";
            } else if (persons.size() == 1) {
                return "redirect:persons/" + persons.get(0).getId();
            } else {
                model.addAttribute("persons", persons);
                return "persons/list";
            }

        } catch (InvalidCriteriaException ice) {
            result.addError(new FieldError("criteriaDto", ice.getFieldName(),
                    messageSource.getMessage(ice.getMessageKey(), null, locale)));
            return "persons/search";
        }
    }

}
