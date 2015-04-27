package com.pr.search;

import com.liferay.portal.util.PortalUtil;
import com.pr.FieldGroup;
import com.pr.ents.Person;
import com.pr.repos.PersonRepo;
import com.pr.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by iuliana.cosmina on 4/18/15.
 */
@Controller("personSearch")
@RequestMapping("VIEW")
public class PersonSearchController {

    private Logger logger = LoggerFactory.getLogger(PersonSearchController.class);

    @Autowired
    private PersonRepo personRepo;

    /**
     * @param model
     * @return
     */
    @RenderMapping
    public String render(Model model, RenderRequest request) {
        logger.info("Render Request performed!");
        String fieldName = (String) model.asMap().get("fieldName");
        String fieldValue = (String) model.asMap().get("fieldValue");
        String exactMatchStr = (String)  model.asMap().get("exactMatch");

        logger.info("RENDER: Performing search for parameters: {}, {}, {} ", new Object[]{fieldName, fieldValue, exactMatchStr});

        model.addAttribute("fieldName", fieldName);
        model.addAttribute("fieldValue", fieldValue);
        model.addAttribute("exactMatch", exactMatchStr);

        model.addAttribute("persons", revtrieveList(fieldName, fieldValue, exactMatchStr, model));

        return "search";
    }

    /**
     * @param request
     * @param response
     */
    @ActionMapping("search")
    public void actionSearch(ActionRequest request, ActionResponse response, Model model) {
        logger.info("Action Request Search performed!");

        String fieldName = request.getParameter("fieldName");
        String fieldValue = request.getParameter("fieldValue");
        String exactMatchStr = request.getParameter("exactMatch");
        logger.info("ACTION: Performing search for parameters: {}, {}, {} ", new Object[]{fieldName, fieldValue, exactMatchStr});

        model.addAttribute("fieldName", fieldName);
        model.addAttribute("fieldValue", fieldValue);
        model.addAttribute("exactMatch", exactMatchStr);
    }


    @ActionMapping("delete")
    public void actionDelete(@RequestParam Long personId) {
        logger.info("ACTION: Deleting person with id= " + personId);
        personRepo.delete(personId);
    }

    //--------------------- Utility methods ---------------------

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private List<Person> revtrieveList(String fieldName, String fieldValue, String exactMatchStr, Model model) {
        boolean exactMatch = exactMatchStr != null && exactMatchStr.equalsIgnoreCase("on") ? true : false;

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
        } else {
            persons = personRepo.findAll();
        }

        return persons;
    }
}
