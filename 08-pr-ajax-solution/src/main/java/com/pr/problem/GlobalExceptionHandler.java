package com.pr.problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by iuliana.cosmina on 3/22/15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler
    public ModelAndView conflict(NotFoundException nfe, HttpServletRequest req) {
        logger.error("Item requested does not exist");
        ModelAndView mav = new ModelAndView();
        mav.addObject("problem", nfe.getObjType() + " with ID=" + nfe.getObjIdentifier() + " not found for : "
                + req.getRequestURL());
        mav.setViewName("error");
        nfe.printStackTrace();
        return mav;
    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e)
            throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(),
                ResponseStatus.class) != null) {
            // we test to see if the exception is annotated with @ResponseStatus
            // if it is,we re-throw it and let Spring handle it.
            throw e;
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("problem", e);
        e.printStackTrace();
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }


}
