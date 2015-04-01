package com.pr;

import com.pr.ents.Person;
import com.pr.form.CriteriaDto;
import com.pr.form.FieldGroup;
import com.pr.repos.PersonRepo;
import com.pr.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iuliana.cosmina on 3/30/15.
 */
@Controller
@RequestMapping(value = "/persons")
public class PersonsListController {

    private Logger logger = LoggerFactory.getLogger(PersonsListController.class);

    private PersonRepo personRepo;

    @Autowired
    public PersonsListController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    /**
     * Handles requests to list all persons.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("persons", personRepo.findAll());
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
    public String processSubmit(@Valid CriteriaDto criteria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "persons/search";
        }
        List<Person> persons = null;
        FieldGroup fg = FieldGroup.getField(criteria.getFieldName());
        switch (fg) {
            case FIRSTNAME:
                persons = criteria.getExactMatch() ? personRepo.getByFirstname(criteria.getFieldValue())
                        : personRepo.getByFirstnameLike(criteria.getFieldValue());
                break;
            case LASTNAME:
                persons = criteria.getExactMatch() ? personRepo.getByLastname(criteria.getFieldValue())
                        : personRepo.getByLastnameLike(criteria.getFieldValue());
                break;
            case DOB:
                Date date = null;
                try {
                    date = new DateFormatter().parse(criteria.getFieldValue(), null);
                } catch (ParseException e) {
                    result.addError(new FieldError("criteriaDto", "fieldValue", "invalid date"));
                    return "persons/search";
                }
                persons = personRepo.getByDateOfBirth(date);
                break;
            case HOSPITAL:
                persons = criteria.getExactMatch() ? personRepo.getByHospitalName(criteria.getFieldValue())
                        : personRepo.getByHospitalNameLike(criteria.getFieldValue());
                break;
            case PNC:
                persons = criteria.getExactMatch() ? personRepo.getByPnc(criteria.getFieldValue())
                        : personRepo.getByPncLike(criteria.getFieldValue());
                break;
        }

        if(persons.size() == 0) {
            result.addError(new FieldError("criteriaDto", "noResults","No result matching the criteria." ));
            return "persons/search";
        } else if (persons.size() == 1) {
            return "redirect:persons/" + persons.get(0).getId();
        } else {
            model.addAttribute("persons", persons);
            return "persons/list";
        }
    }

}
