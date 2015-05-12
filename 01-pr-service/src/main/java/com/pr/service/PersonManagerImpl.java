package com.pr.service;

import com.pr.dto.CriteriaDto;
import com.pr.dto.FieldGroup;
import com.pr.ents.Person;
import com.pr.problem.InvalidCriteriaException;
import com.pr.repos.PersonRepo;
import com.pr.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iuliana.cosmina on 5/11/15.
 */
@Service
public class PersonManagerImpl implements PersonManager {

    @Autowired
    PersonRepo personRepo;

    @Override
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personRepo.findOne(id);
    }

    @Override
    public Person save(Person person) {
        return personRepo.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepo.delete(person);
    }

    @Override
    public void deleteById(Long id) {
        personRepo.delete(id);
    }

    @Override
    public List<Person> getByLastname(String lastName) {
        return personRepo.getByLastname(lastName);
    }

    @Override
    public List<Person> getByLastnameLike(String lastName) {
        return personRepo.getByLastnameLike(lastName);
    }

    @Override
    public List<Person> getByFirstnameOrLastname(String name) {
        return personRepo.getByFirstnameOrLastname(name);
    }

    @Override
    public List<Person> getByFirstname(String firstName) {
        return personRepo.getByFirstname(firstName);
    }

    @Override
    public List<Person> getByFirstnameLike(String firstName) {
        return personRepo.getByFirstnameLike(firstName);
    }

    @Override
    public List<Person> getByPnc(String pnc) {
        return personRepo.getByPnc(pnc);
    }

    @Override
    public List<Person> getByPncLike(String pnc) {
        return personRepo.getByPncLike(pnc);
    }

    @Override
    public List<Person> getByFirstnameOrLastnameLike(String name) {
        return personRepo.getByFirstnameOrLastnameLike(name);
    }

    @Override
    public List<Person> getByDateOfBirth(Date dob) {
        return personRepo.getByDateOfBirth(dob);
    }

    @Override
    public List<Person> getByHospital(String code) {
        return personRepo.getByHospital(code);
    }

    @Override
    public List<Person> getByHospitalName(String name) {
        return personRepo.getByHospitalName(name);
    }

    @Override
    public List<Person> getByHospitalNameLike(String name) {
        return personRepo.getByHospitalNameLike(name);
    }

    @Override
    public List<Person> getByCriteriaDto(CriteriaDto criteria) throws InvalidCriteriaException {
        List<Person> persons = new ArrayList<>();
        FieldGroup fg = FieldGroup.getField(criteria.getFieldName());
        switch (fg) {
            case FIRSTNAME:
                persons = criteria.getExactMatch() ? personRepo.getByFirstname(criteria.getFieldValue())
                        : personRepo.getByFirstnameLike(criteria.getFieldValue());
                break;
            case LASTNAME:
                persons = criteria.getExactMatch() ? personRepo.getByLastname(criteria.getFieldValue())
                        : personRepo.getByLastnameLike(criteria.getFieldValue());
                break;
            case DOB:
                Date date = null;
                try {
                    date = new DateFormatter().parse(criteria.getFieldValue(), null);
                } catch (ParseException e) {
                    throw new InvalidCriteriaException("fieldValue", "invalid.date");
                }
                persons = personRepo.getByDateOfBirth(date);
                break;
            case HOSPITAL:
                persons = criteria.getExactMatch() ? personRepo.getByHospitalName(criteria.getFieldValue())
                        : personRepo.getByHospitalNameLike(criteria.getFieldValue());
                break;
            case PNC:
                persons = criteria.getExactMatch() ? personRepo.getByPnc(criteria.getFieldValue())
                        : personRepo.getByPncLike(criteria.getFieldValue());
                break;
        }
        return persons;
    }
}
