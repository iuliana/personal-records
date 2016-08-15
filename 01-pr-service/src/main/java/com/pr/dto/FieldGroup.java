package com.pr.dto;

/**
 * Created by iuliana.cosmina on 3/31/15.
 */
public enum FieldGroup {
    FIRSTNAME,
    LASTNAME,
    DOB,
    PNC,
    HOSPITAL;

    public static FieldGroup getField(String field){
        return FieldGroup.valueOf(field.toUpperCase());
    }
}
