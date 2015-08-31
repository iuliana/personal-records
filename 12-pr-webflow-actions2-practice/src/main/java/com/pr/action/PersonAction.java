package com.pr.action;

import com.pr.WebFlowAction;
import com.pr.base.PncBuilder;
import com.pr.ents.Person;
import com.pr.problem.DuplicatePersonException;
import com.pr.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Created by iuliana.cosmina on 8/31/15.
 */
@WebFlowAction
public class PersonAction extends MultiAction {

    @Autowired
    PersonManager personManager;

    public Event isNewPerson(RequestContext context) throws Exception {
        Person person = (Person) context.getFlowScope().get("person");
        String pnc = PncBuilder.build(person);
        Person oldPerson = personManager.getByPnc(pnc);
        if (oldPerson == null) {
            return success();
        } else {
            return error(new DuplicatePersonException(oldPerson));
        }
    }
}
