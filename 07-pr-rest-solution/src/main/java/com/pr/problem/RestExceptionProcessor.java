package com.pr.problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by iuliana.cosmina on 5/23/15.
 */
@ControllerAdvice(basePackages = "com.pr.rest")
public class RestExceptionProcessor {
    private Logger logger = LoggerFactory.getLogger(RestExceptionProcessor.class);

    /**
     * Maps NotFoundException to a 404 Not Found HTTP status code.
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This entity is not found in the system")
    @ExceptionHandler({NotFoundException.class})
    public void handleNotFound(NotFoundException nfe) {
        // just return empty 404
        logger.info("-----> Entity " + nfe.getObjType() + " with identifier" + nfe.getObjIdentifier() + "not found.");
    }

    /**
     * Maps DataIntegrityViolationException to a 409 Conflict HTTP status code.
     */
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Another entity with the same identity exists")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void handleAlreadyExists() {
        // just return empty 409
        logger.info("-----> Entity save operation failure");
    }


 /*   @Autowired
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
    }*/
}
