package con.pr.persons;

import com.pr.ents.Hospital;
import com.pr.model.PersonObjectModel;
import com.pr.service.HospitalManager;
import com.pr.service.PersonService;
import com.pr.validator.HospitalValidator;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.State;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
/**
 * Created by iuliana.cosmina on 7/17/15.
 */
@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class NewPersonFlowTest extends AbstractXmlFlowExecutionTests {

    private static final String ENTER_PERSON_INFO = "enterPersonInfo";
    private static final String ENTER_IC_INFO = "enterIdentityCardInfo";

    private static final String ISNEW_PERSON = "isNewPerson";
    private static final String NEW_HOSPITAL = "newHospital";

    private static final String END = "end";

    @Mock HospitalManager hospitalManager;

    @Mock PersonService personService;

    @Mock HospitalValidator hospitalValidator;

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
        FlowDefinitionResource resource = resourceFactory.createFileResource("src/main/webapp/WEB-INF/persons/newPerson/newPerson-flow.xml");
        assertNotNull(resource);
        return resource;
    }

   /* @Override
    protected FlowDefinitionResource[]
    getModelResources(FlowDefinitionResourceFactory resourceFactory) {
        FlowDefinitionResource[] flowDefinitionResources =
                new FlowDefinitionResource[2];
        flowDefinitionResources[0] =
                resourceFactory.createResource("src/main/webapp/WEB-INF/persons/newPerson/newPerson-flow.xml");
        flowDefinitionResources[1] =
                resourceFactory.createResource("src/main/webapp/WEB-INF/hospitals/newHospital/newHospital-flow.xml");

        return flowDefinitionResources;
    }*/


    @Override
    protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
        builderContext.registerBean("hospitalManager", hospitalManager);
        builderContext.registerBean("personService", personService);
        builderContext.registerBean("hospitalValidator", hospitalValidator);

        // setup newHospital subflow
        Flow newHospital = new Flow("newHospital");
        State start = new State(newHospital, "newHospital") {
            @Override
            protected void doEnter(RequestControlContext context)
                    throws FlowExecutionException {
                // empty
            }
        };
        newHospital.setStartState(start);
        builderContext.registerSubflow(newHospital);
    }

    @Override
    protected void registerMockFlowBeans(ConfigurableBeanFactory flowBeanFactory){
        flowBeanFactory.registerSingleton("hospitalManager", hospitalManager);
    }

    @Test
    public void testStart() throws Exception {
        MockExternalContext context = new MockExternalContext();
        when(hospitalManager.findAll()).thenReturn(new ArrayList<>());
        PersonObjectModel person = createMockPerson();
        MutableAttributeMap input = new LocalAttributeMap<>();
        input.put("person", person);

        startFlow(input,context);
        assertFlowExecutionActive();
        assertCurrentStateEquals(ENTER_PERSON_INFO);
    }

    @Ignore
    public void testAddNewHospital() throws Exception {
        MockExternalContext context = new MockExternalContext();

        when(hospitalManager.findAll()).thenReturn(new ArrayList<>());
        when(personService.isNewPerson(any(PersonObjectModel.class))).thenReturn(true);

        PersonObjectModel person = createMockPerson();
        MutableAttributeMap input = new LocalAttributeMap<>();
        input.put("person", person);
        input.put("hospital", person.getHospital());

        startFlow(input,context);
        assertFlowExecutionActive();
        assertCurrentStateEquals(ENTER_PERSON_INFO);

        context.setEventId("addHospital");
        resumeFlow(context);
        assertFlowExecutionActive();
        assertCurrentStateEquals(NEW_HOSPITAL);
    }

    @Test
    public void testEnterIdentityCard() throws Exception {
        MockExternalContext context = new MockExternalContext();

        when(hospitalManager.findAll()).thenReturn(new ArrayList<>());
        when(personService.isNewPerson(any(PersonObjectModel.class))).thenReturn(true);

        PersonObjectModel person = createMockPerson();
        MutableAttributeMap input = new LocalAttributeMap<>();
        input.put("person", person);

        startFlow(input,context);
        assertFlowExecutionActive();
        assertCurrentStateEquals(ENTER_PERSON_INFO);

        context.setEventId("next");
        resumeFlow(context);
        assertFlowExecutionActive();
        assertCurrentStateEquals(ENTER_IC_INFO);
    }

    private PersonObjectModel createMockPerson() {
        PersonObjectModel person = new PersonObjectModel();
        person.setFirstName("Test");
        person.setLastName("User");
        person.setDateOfBirth(DateTime.parse("1951-05-14").toDate());
        person.setHospital(createMockHospital());
        return person;
    }

    private Hospital createMockHospital() {
        Hospital hospital = new Hospital();
        hospital.setName("Test Hospital");
        hospital.setCode("112299");
        hospital.setLocation("somewhere");
        return hospital;
    }

}
