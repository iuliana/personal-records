package com.pr;

import com.pr.ents.Person;
import com.pr.repos.HospitalRepo;
import com.pr.repos.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by iuliana.cosmina on 3/22/15.
 */
@Controller
@RequestMapping("/hospitals")
public class HospitalsController {
    private Logger logger = LoggerFactory.getLogger(PersonsController.class);

    private HospitalRepo hospitalRepo;

    private PersonRepo personRepo;

    @Autowired(required = true)
    public void setHospitalRepo(HospitalRepo hospitalRepo) {
        this.hospitalRepo = hospitalRepo;
    }

    @Autowired(required = true)
    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    /**
     * Handles requests to list all persons.
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Populating model with list...");
        model.addAttribute("hospitals", hospitalRepo.findAll());
        return "hospitals/list";
    }

    /**
     * Handles requests to shows details about one person.
     */
    @RequestMapping(value = "/{code:[\\d]*}", method = RequestMethod.GET)
    public String show(@PathVariable String code, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("persons", personRepo.getByHospital(code));
        return "redirect:/persons/list";
    }
}
