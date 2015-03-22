package com.pr.problem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by iuliana.cosmina on 3/22/15.
 */
public class NotFoundException extends Exception {

    private Long objIdentifier;

    private String objectType;

    public <T> NotFoundException(Class<T> cls, Long id) {
        super(cls.getSimpleName() + " with id: " + id + " does not exist!");
        objectType = cls.getSimpleName();
    }

    public Long getObjIdentifier() {
        return objIdentifier;
    }

    public String getObjectType() {
        return objectType;
    }
}
