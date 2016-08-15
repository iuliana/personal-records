package com.pr;

import com.pr.dto.CriteriaDto;
import org.hibernate.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by iuliana.cosmina on 10/11/15.
 */
@Controller
@RequestMapping(value = "/rest-search")
public class RestFormController {

    /**
     * Displays the REST customized person search form to the user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String search() {
        return "persons/rest-search";
    }

}
