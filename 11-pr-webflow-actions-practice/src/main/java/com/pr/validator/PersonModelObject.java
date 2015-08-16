package com.pr.validator;


import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.validation.ValidationContext;

/**
 * Created by iuliana.cosmina on 8/16/15.
 */
public class PersonModelObject {

    public void validateEnterPersonInfo(ValidationContext validationContext){
        validationContext.getMessageContext().
                addMessage(new MessageBuilder().error()
                        .source("person")
                        .code("Size.person.firstName")
                        .defaultText
                                ("Length must be between 2 and 50").build());
    }
}
