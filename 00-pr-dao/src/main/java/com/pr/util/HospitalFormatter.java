package com.pr.util;

import com.pr.ents.Hospital;
import com.pr.repos.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by iuliana.cosmina on 3/30/15.
 * Description: class which uses an id to transform it into a Hospital instance
 */
public class HospitalFormatter implements Formatter<Hospital> {

    @Autowired
    HospitalRepo hospitalRepo;

    @Override
    public Hospital parse(String s, Locale locale) throws ParseException {
        Long  id = Long.parseLong(s);
        return hospitalRepo.findOne(id);
    }

    @Override
    public String print(Hospital hospital, Locale locale) {
        return hospital.getName();
    }
}
