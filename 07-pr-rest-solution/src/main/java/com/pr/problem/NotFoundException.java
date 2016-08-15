package com.pr.problem;

/**
 * Created by iuliana.cosmina on 3/22/15.
 */
public class NotFoundException extends Exception {

    private String objIdentifier;

    private String objType;

    public <T> NotFoundException(Class<T> cls, String id) {
        super(cls.getSimpleName() + " with id: " + id + " does not exist!");
        objIdentifier = id;
        objType = cls.getSimpleName();
    }

    public String getObjIdentifier() {
        return objIdentifier;
    }

    public String getObjType() {
        return objType;
    }
}
