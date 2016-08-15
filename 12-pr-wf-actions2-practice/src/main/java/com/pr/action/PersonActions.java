package com.pr.action;

import com.pr.WebFlowAction;
import com.pr.ents.Person;
import com.pr.model.PersonObjectModel;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by iuliana.cosmina on 9/2/15.
 */
@WebFlowAction
public class PersonActions extends MultiAction {

    public Event isAdult(RequestContext context) {
        PersonObjectModel person = (PersonObjectModel) context.getFlowScope().get("person");
        Date input = person.getDateOfBirth();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate birthday = zdt.toLocalDate();
        long yearsDelta = birthday.until(LocalDate.now(), ChronoUnit.YEARS);
        if(yearsDelta>=18){
            return success();
        } else {
            context
                    .getMessageContext()
                    .addMessage(
                            new MessageBuilder()
                                    .error()
                                    .source("dob")
                                    .code("person.not.adult")
                                    .defaultText(
                                            "Bank accounts can be added only for persons over 18 years old.")
                                    .build());
            return error();
        }
    }

}
