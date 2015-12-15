package com.pr.validator;

import com.pr.ents.Hospital;
import com.pr.service.HospitalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 9/6/15.
 */
@Component("hospitalValidator")
public class HospitalValidator {

    @Autowired
    HospitalManager hospitalManager;

    public void validateEnterHospitalInfo(Hospital hospital, ValidationContext validationContext) {
        Hospital existingHospital = hospitalManager.findByCode(hospital.getCode());
        if (existingHospital != null) {
            String existingCode = existingHospital.getCode();
            if (hospital.getCode().equalsIgnoreCase(existingCode)) {
                validationContext.getMessageContext().
                        addMessage(new MessageBuilder().error()
                                .source("code")
                                .code("duplicate.hospital.code")
                                .defaultText
                                        ("A hospital already exists in the system with this number: " +
                                                        existingHospital.getCode()).build());
            }
        }
    }
}
