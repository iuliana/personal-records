package com.pr.add;

import com.pr.ents.Hospital;
import com.pr.ents.IdentityCard;
import com.pr.ents.Person;
import com.pr.repos.HospitalRepo;
import com.pr.repos.PersonRepo;
import com.pr.service.HospitalManager;
import com.pr.service.PersonManager;
import com.pr.util.DateFormatter;
import com.pr.util.HospitalFormatter;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 4/26/15.
 */
@Controller("personAdd")
@RequestMapping("VIEW")
public class PersonAddController {

    private Logger logger = LoggerFactory.getLogger(PersonAddController.class);

    @Autowired
    private PersonManager personManager;

    @Autowired
    private HospitalManager hospitalManager;

    @Autowired
    private MessageSource messageSource;

    @RenderMapping
    public String render(Model model) {
        logger.info("RENDER: rendering add person form");
        // Workaround to get the errors form-validation from actionrequest
        Errors errors = (Errors) model.asMap().get("errors");
        if (errors != null) {
            model.addAttribute("org.springframework.validation.BindingResult.person", errors);
        } else {
            model.addAttribute(new Person());
        }
        return "add";
    }

    @RenderMapping(params = "action=clean")
    public String renderNew(Model model) {
        model.asMap().remove("org.springframework.validation.BindingResult.person");
        model.addAttribute(new Person());
        return "add";
    }

    @ActionMapping("add")
    public void addPerson(@Valid @ModelAttribute Person person, BindingResult result, ActionRequest actionRequest, ActionResponse actionResponse,
                          SessionStatus sessionStatus, Model model) {
        if (!result.hasErrors()) {
            logger.info("ACTION: action saving person = " + person);
            try {
                DateTime dt1 = DateTime.now();
                DateTime dt2 = dt1.plusYears(10);
                IdentityCard identityCard = new IdentityCard(person, "BL", "112233", dt1.toDate(), dt2.toDate());
                identityCard.setAddress("-");
                person.setIdentityCard(identityCard);
                personManager.save(person);
                model.addAttribute("message", messageSource.getMessage("label.Person.saved", null, actionRequest.getLocale()));
                sessionStatus.setComplete();
            } catch (Exception e) {
                logger.error("Unexpected error when saving person.", e);
                model.addAttribute("error", "Internal Error. Contact Administrator.");
            }
        } else {
            logger.info("Validation failed");
            model.addAttribute("errors", result);
        }
    }

    @ModelAttribute
    private List<Hospital> getHospitals() {
        return hospitalManager.findAll();
    }


    @Autowired
    @Qualifier("typeConversionService")
    ConversionService conversionService;

    @Autowired
    @Qualifier("validator")
    Validator validator;

    @InitBinder("person")
    public void initBinder(WebDataBinder binder) {
        binder.setConversionService(conversionService);
        binder.setValidator(validator);
    }

}
