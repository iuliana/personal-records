package com.pr.service;

import com.pr.dto.CriteriaDto;
import com.pr.ents.Person;
import com.pr.problem.InvalidCriteriaException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by iuliana.cosmina on 5/11/15.
 * Description: Interface used to describe behaviour of a Person service class
 */
public interface PersonManager extends BaseManager<Person> {

    @Transactional(readOnly = true)
    List<Person> getByLastname(String lastName);

    @Transactional(readOnly = true)
    List<Person> getByLastnameLike(String lastName);

    @Transactional(readOnly = true)
    List<Person> getByFirstnameOrLastname(String name);

    @Transactional(readOnly = true)
    List<Person> getByFirstname(String firstName);

    @Transactional(readOnly = true)
    List<Person> getByFirstnameLike(String firstName);

    @Transactional(readOnly = true)
    Person getByPnc(String pnc);

    @Transactional(readOnly = true)
    List<Person> getByPncLike(String pnc);

    @Transactional(readOnly = true)
    List<Person> getByFirstnameOrLastnameLike(String name);

    @Transactional(readOnly = true)
    List<Person> getByDateOfBirth(Date dob);

    @Transactional(readOnly = true)
    List<Person> getByHospital(String code);

    @Transactional(readOnly = true)
    List<Person> getByHospitalName(String name);

    @Transactional(readOnly = true)
    List<Person> getByHospitalNameLike(String name);

    @Transactional(readOnly = true)
    List<Person> getByCriteriaDto(CriteriaDto criteria) throws InvalidCriteriaException;
}
