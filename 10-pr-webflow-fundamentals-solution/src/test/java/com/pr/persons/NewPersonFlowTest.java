package com.pr.persons;

import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

/**
 * Created by iuliana.cosmina on 7/17/15.
 */
public class NewPersonFlowTest extends AbstractXmlFlowExecutionTests {

    private static final String ENTER_PERSON_INFO = "enterPersonInfo";

    private static final String REVIEW_PERSON = "reviewPerson";

    private static final String END = "end";

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
        return resourceFactory.createFileResource("src/main/webapp/WEB-INF/persons/newPerson/newPerson-flow.xml");
    }

    public void testStart() throws Exception {
        startFlow(new MockExternalContext());
        assertCurrentStateEquals(ENTER_PERSON_INFO);
    }


    public void testEnterPersonInfoProceed() throws Exception {
        setCurrentState(ENTER_PERSON_INFO);
        MockExternalContext externalContext = new MockExternalContext();
        externalContext.setEventId("proceed");
        resumeFlow(externalContext);
        assertCurrentStateEquals(REVIEW_PERSON);
    }

    public void testReviewPersonConfirm() throws Exception {
        setCurrentState(REVIEW_PERSON);
        MockExternalContext externalContext = new MockExternalContext();
        externalContext.setEventId("confirm");
        resumeFlow(externalContext);
        assertFlowExecutionEnded();
        assertFlowExecutionOutcomeEquals(END);
        assertTrue(externalContext.getExternalRedirectRequested());
        assertEquals("contextRelative:/persons/1",
                externalContext.getExternalRedirectUrl());
    }

}
