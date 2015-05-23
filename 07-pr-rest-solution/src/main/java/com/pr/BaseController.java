package com.pr;

import com.pr.service.HospitalManager;
import com.pr.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by iuliana.cosmina on 5/21/15.
 */
public class BaseController {

    protected PersonManager personManager;
    protected HospitalManager hospitalManager;

    @Autowired
    public void setPersonsController(PersonManager personManager) {
        this.personManager = personManager;
    }

    @Autowired
    public void setHospitalManager(HospitalManager hospitalManager) {
        this.hospitalManager = hospitalManager;
    }

}
