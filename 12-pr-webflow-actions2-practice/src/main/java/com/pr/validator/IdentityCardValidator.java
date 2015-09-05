package com.pr.validator;

import com.pr.ents.IdentityCard;
import com.pr.ents.Person;
import com.pr.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 8/19/15.
 */
@Component("identityCardValidator")
public class IdentityCardValidator {

    @Autowired
    PersonManager personManager;

    public void validateEnterIdentityCardInfo(IdentityCard identityCard, ValidationContext validationContext) {
        Person existingPerson = personManager.getByPnc(identityCard.getPnc());
        String existingIcNumber = existingPerson.getIdentityCard().getNumber();
        if (identityCard.getNumber().equalsIgnoreCase(existingIcNumber)) {
            validationContext.getMessageContext().
                    addMessage(new MessageBuilder().error()
                            .source("number")
                            .code("duplicate.ic.number")
                            .defaultText
                                    ("An IdentityCard already exists in the system with this number: " +
                                            existingIcNumber).build());
        }
    }
}
