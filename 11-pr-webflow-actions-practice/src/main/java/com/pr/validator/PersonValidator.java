package com.pr.validator;

import com.pr.ents.Person;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.validation.Errors;

/**
 * Created by iuliana.cosmina on 8/16/15.
 */
public class PersonValidator {

    public boolean check(Person person, MessageContext messageContext) {
        messageContext.addMessage
                (new MessageBuilder().error().source("firstName")
                        .code("Size.person.firstName").build());
        return true;
    }

    public boolean check2(Person person, Errors errors) {
        errors.rejectValue("person",
                "Size.person.firstName",
                "Length must be between 2 and 50");
        return true;
    }


}
