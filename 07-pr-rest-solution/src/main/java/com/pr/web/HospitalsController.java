package com.pr.web;

import com.pr.BaseController;
import com.pr.service.HospitalManager;
import com.pr.service.PersonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by iuliana.cosmina on 3/22/15.
 */
@Controller
@RequestMapping("/hospitals")
public class HospitalsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(PersonsController.class);

    /**
     * Handles requests to list all persons.
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Populating model with list...");
        model.addAttribute("hospitals", hospitalManager.findAll());
        return "hospitals/list";
    }

    /**
     * Handles requests to shows details about one person.
     */
    @RequestMapping(value = "/{code:[\\d]*}", method = RequestMethod.GET)
    public String showBornHere(@PathVariable String code, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("persons", personManager.getByHospital(code));
        return "redirect:/persons/list";
    }
}
