package com.pr;

import com.liferay.portal.kernel.util.ParamUtil;
import com.pr.ents.Person;
import com.pr.repos.PersonRepo;
import com.pr.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;

import javax.portlet.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iuliana.cosmina on 4/18/15.
 */
@Controller("personSearch")
@RequestMapping("VIEW")
public class PersonSearchController {

    private Logger logger = LoggerFactory.getLogger(PersonSearchController.class);

    private PersonRepo personRepo;

    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public PersonSearchController() {
       // required by Liferay
    }


    /**
     * @param model
     * @return
     */
    @RenderMapping
    public String render(Model model, RenderRequest request) {
        logger.info("Render Request performed!");
        String fieldName = getHttpRequestParam(request, "fieldName");
        String fieldValue = getHttpRequestParam(request, "fieldValue");
        String exactMatchStr = getHttpRequestParam(request, "exactMatch");

        model.addAttribute("fieldName", fieldName);
        model.addAttribute("fieldValue", fieldValue);
        model.addAttribute("exactMatch", exactMatchStr);

        logger.info("Performing search for parameters: {}, {}, {} ", new Object[]{fieldName, fieldValue, exactMatchStr});

        boolean exactMatch = exactMatchStr != null && exactMatchStr.equalsIgnoreCase("on")? true: false;

        List<Person> persons = new ArrayList<Person>();
        if (!isEmpty(fieldName) && !isEmpty(fieldValue)) {
            FieldGroup fg = FieldGroup.getField(fieldName);
            switch (fg) {
                case FIRSTNAME:
                    persons = exactMatch ? personRepo.getByFirstname(fieldValue)
                            : personRepo.getByFirstnameLike(fieldValue);
                    break;
                case LASTNAME:
                    persons = exactMatch ? personRepo.getByLastname(fieldValue)
                            : personRepo.getByLastnameLike(fieldValue);
                    break;
                case DOB:
                    Date date = null;
                    try {
                        date = new DateFormatter().parse(fieldValue, null);
                        persons = personRepo.getByDateOfBirth(date);
                    } catch (ParseException e) {
                        model.addAttribute("error", "Invalid Date");
                    }
                    break;
                case HOSPITAL:
                    persons = exactMatch ? personRepo.getByHospitalName(fieldValue)
                            : personRepo.getByHospitalNameLike(fieldValue);
                    break;
                case PNC:
                    persons = exactMatch ? personRepo.getByPnc(fieldValue)
                            : personRepo.getByPncLike(fieldValue);
                    break;
            }
        }

       model.addAttribute("persons", persons);

        return "search";
    }

    /**
     * @param request
     * @param response
     */
    @ActionMapping(value = "search")
    public void actionSearch(ActionRequest request, ActionResponse response) {
        logger.info("Action Request Search performed!");

        String fieldName = request.getParameter("fieldName");
        String fieldValue = (String)request.getAttribute("fieldValue");
        String exactMatchStr = ParamUtil.getString(request, "exactMatch");

        logger.info("ACTION: Performing search for parameters: {}, {}, {} ", new Object[]{fieldName, fieldValue, exactMatchStr});

    }

    public static String getHttpRequestParam(RenderRequest renderRequest, String paramName) {
        HttpServletRequest convertReq = PortalUtil.getHttpServletRequest(renderRequest);
        HttpServletRequest originalReq = PortalUtil.getOriginalServletRequest(convertReq);
        return originalReq.getParameter(paramName);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
