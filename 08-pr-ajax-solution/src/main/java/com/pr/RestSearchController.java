package com.pr;

import com.pr.dto.CriteriaDto;
import com.pr.ents.Person;
import com.pr.problem.InvalidCriteriaException;
import com.pr.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iuliana.cosmina on 10/11/15.
 * Description: REST controller class used to receive JSON search requests from the interface.
 */
@RestController
@RequestMapping(value = "/rest-search/perform")
public class RestSearchController {

    @Autowired
    protected PersonManager personManager;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getAll(@RequestBody CriteriaDto criteria) throws InvalidCriteriaException {
        if (criteria.getFieldValue() == null || criteria.getFieldValue().isEmpty()) {
            return new ArrayList<>();
        }
        return personManager.getByCriteriaDto(criteria);
        /*if (true) {
            throw new NullPointerException("Intentional bubu");
        }*/

    }
}
