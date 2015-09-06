package com.pr.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.execution.*;

/**
 * Created by iuliana.cosmina on 7/12/15.
 */
public class AuditFlowExecutorListener extends FlowExecutionListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(AuditFlowExecutorListener.class);

    public void stateEntering(RequestContext context, StateDefinition state) throws EnterStateVetoException {
        logger.info("Flow is entering state " + state.getId());
    }

    public void eventSignaled(RequestContext context, Event event) {
        logger.info("Event " + event.getId() + " was triggered in state " + context.getCurrentState().getId());
    }

    public void exceptionThrown(RequestContext context, FlowExecutionException exception) {
        logger.info("Exception was thrown in state " + exception.getStateId() + " for flow " + exception.getFlowId()
                + ", message: " + exception.getMessage());
    }

}
