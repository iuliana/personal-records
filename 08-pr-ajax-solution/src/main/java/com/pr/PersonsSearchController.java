package com.pr;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by iuliana.cosmina on 3/30/15.
 */
@Controller
@RequestMapping(value = "/persons")
public class PersonsSearchController {

    private Logger logger = LoggerFactory.getLogger(PersonsSearchController.class);

    private PersonManager personManager;

    @Autowired
    public PersonsSearchController(PersonManager personManager) {
        this.personManager = personManager;
    }

    @Autowired
    private MessageSource messageSource;

    /**
     * Displays a person search form to the user
     *
     * @param criteria
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(CriteriaDto criteria,  Model model, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);
        model.addAttribute("fieldValueErrMessage", messageSource.getMessage("NotEmpty.fieldValue", null, locale));
        model.addAttribute("fieldDateErrMessage", messageSource.getMessage("typeMismatch.dateOfBirth", null, locale));
        return "persons/search";
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Person> getPersons(CriteriaDto criteria) {
        try {
            List<Person> persons = personManager.getByCriteriaDto(criteria);
            return persons;
        } catch (InvalidCriteriaException ice) {
            // TODO
        }
        return new ArrayList<>();
    }

}
