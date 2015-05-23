package com.pr.rest;

import com.pr.BaseController;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iuliana.cosmina on 5/21/15.
 */
@RestController
@RequestMapping(value = "/rest-hospitals")
public class HospitalsRestController extends BaseController {

    /**
     * Provide the details of a hospital with the given code.
     */
    @RequestMapping(value = "/{code}", method = RequestMethod.GET, produces = "application/json")
    public Hospital getHospital(@PathVariable String code) {
        return hospitalManager.findByCode(code);
    }
}
