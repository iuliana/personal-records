package com.pr.action;

import com.pr.WebFlowAction;
import com.pr.ents.Account;
import com.pr.ents.Person;
import com.pr.service.AccountManager;
import com.pr.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Created by iuliana.cosmina on 9/3/15.
 */
@WebFlowAction
public class AccountActions extends MultiAction {

    @Autowired
    AccountManager accountManager;

    @Autowired
    PersonManager personManager;

    public Event saveAccount(RequestContext context) {
        Person person = (Person) context.getFlowScope().get("existingPerson");
        try {
            person = personManager.findById(person.getId());

            Account account = (Account) context.getFlowScope().get("account");
            account.setPerson(person);
            accountManager.save(account);
        } catch (Exception e) {
            context.getMessageContext().
                    addMessage(new MessageBuilder().error()
                            .source("iban")
                            .code("duplicate.Account.iban")
                            .defaultText
                                    ("An account already exists with this IBAN code.").build());
            return error();
        }
        return success();
    }
}
