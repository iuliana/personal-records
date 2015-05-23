package com.pr.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by iuliana.cosmina on 5/23/15.
 */
//@ControllerAdvice(basePackages = "com.pr.rest")
public class RestExceptionProcessor {

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ResponseBody
    public JsonError personNotFound(HttpServletRequest req, NotFoundException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage("error.no.person.id", null, locale);

        errorMessage += ex.getObjIdentifier();
        String errorURL = req.getRequestURL().toString();

        return new JsonError(errorURL, errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value= HttpStatus.CONFLICT)
    @ResponseBody
    public JsonError personNotFound(HttpServletRequest req, DataIntegrityViolationException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage("error.duplicate.person", null, locale);

        errorMessage += ex.getMessage();
        String errorURL = req.getRequestURL().toString();

        return new JsonError(errorURL, errorMessage);
    }
}
