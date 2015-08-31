package com.pr.hospitals;


import com.pr.service.HospitalManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by iuliana.cosmina on 3/22/15.
 */
@Controller
@RequestMapping("/hospitals")
public class HospitalsController {
    private Logger logger = LoggerFactory.getLogger(HospitalsController.class);

    protected HospitalManager hospitalManager;

    @Autowired
    public HospitalsController(HospitalManager hospitalManager) {
        this.hospitalManager = hospitalManager;
    }

    /**
     * Handles requests to list all persons.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Populating model with list...");
        model.addAttribute("hospitals", hospitalManager.findAll());
        return "hospitals/list";
    }
}
