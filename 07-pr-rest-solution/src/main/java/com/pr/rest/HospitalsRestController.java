package com.pr.rest;

import com.pr.BaseController;
import com.pr.ents.Hospital;
import com.pr.ents.Person;
import com.pr.problem.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by iuliana.cosmina on 5/21/15.
 */
@RestController
@RequestMapping(value = "/rest-hospitals")
public class HospitalsRestController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(HospitalsRestController.class);

    /**
     * Provide the details of a hospital with the given code.
     */
    @RequestMapping(value = "/{code}", method = RequestMethod.GET, produces = "application/json")
    public Hospital getHospital(@PathVariable String code) throws NotFoundException {
        Hospital hospital = hospitalManager.findByCode(code);
        if (hospital == null) {
            throw new NotFoundException(Hospital.class, code);
        }
        return hospital;
    }

    /**
     * Provide a list with all hospitals
     *
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hospital> getAll() {
        logger.info("-----> ALL ");
        return hospitalManager.findAll();
    }

    /**
     * Provide a list with all persons born at a hospital
     *
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{code}/persons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersons(@PathVariable String code) {
        logger.info("-----> ALL PERSONS BORN AT: "  + code);
        return personManager.getByHospital(code);
    }


    /**
     * Create a new hospital
     *
     * @param newHospital
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Hospital createHospital(@RequestBody @Valid Hospital newHospital) {
        logger.info("-----> CREATE");
        Hospital hospital = hospitalManager.save(newHospital);
        logger.info("-----> Hospital: " + hospital);
        return hospital;
    }

    /**
     * Deletes a hospital by code
     *
     * @param code
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/delete/{code}", method = RequestMethod.DELETE)
    public void deleteHospital(@PathVariable String code) throws NotFoundException {
        Hospital hospital = hospitalManager.findByCode(code);
        if (hospital != null) {
            hospitalManager.delete(hospital);
        } else {
            throw new NotFoundException(Hospital.class, code);
        }
    }
}
