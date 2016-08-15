package com.pr.unit;

import com.pr.HospitalsController;
import com.pr.service.HospitalManager;
import com.pr.service.PersonManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by iuliana.cosmina on 3/24/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class HospitalsControllerTest {
    private static final String TEST_CODE = "112244";

    private HospitalsController hospitalsController;

    @Mock
    private PersonManager personManagerMock;
    @Mock
    private HospitalManager hospitalManagerMock;

    @Before
    public void setUp(){
        hospitalsController = new HospitalsController();
        hospitalsController.setHospitalManager(hospitalManagerMock);
        hospitalsController.setPersonRepo(personManagerMock);
    }

    @Test
    public void list() {
        when(hospitalManagerMock.findAll()).thenReturn(new ArrayList<>());

        Model model = new BindingAwareModelMap();
        String view = hospitalsController.list(model);
        assertEquals("hospitals/list", view);
        assertNotNull(model.asMap().get("hospitals"));
    }


    @Test
    public void showBornHere() {
        when(personManagerMock.getByHospital(TEST_CODE)).thenReturn(new ArrayList<>());

        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        String view = hospitalsController.showBornHere(TEST_CODE, attributes);
        assertEquals("redirect:/persons/list", view);

    }

}
