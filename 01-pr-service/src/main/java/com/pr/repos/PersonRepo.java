package com.pr.repos;

import com.pr.ents.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
public interface PersonRepo extends JpaRepository<Person, Long> {

    /**
     * Find all persons with the given lastName.
     */
    @Query("select p from Person p where p.lastName = :lastName")
    List<Person> getByLastname(@Param("lastName") String lastName);

    /**
     * Find all persons with the lastName similar to the given lastName.
     */
    @Query("select p from Person p where p.lastName like %?1%")
    List<Person> getByLastnameLike(String lastName);

    /**
     * Returns all persons with the given name as firstname or lastname.
     */
    @Query("select p from Person p where p.firstName = :name or p.lastName = :name")
    List<Person> getByFirstnameOrLastname(@Param("name") String name);

    /**
     * Find all persons with the given firstName.
     */
    @Query("select p from Person p where p.firstName = :firstName")
    List<Person> getByFirstname(@Param("firstName") String firstName);

    /**
     *
     * Find all persons with the firstName similar to the given firstName.
     */
    @Query("select p from Person p where p.firstName like %?1%")
    List<Person> getByFirstnameLike(String firstName);


    /**
     * Find all persons with the given pnc.
     */
    @Query("select p from Person p where p.identityCard.pnc = :pnc")
    Person getByPnc(@Param("pnc") String pnc);

    /**
     *
     * Find all persons with the pnc similar to the given pnc.
     */
    @Query("select p from Person p where p.identityCard.pnc like %?1%")
    List<Person> getByPncLike(String pnc);

    /**
     * Returns all persons with firstname or lastname similar to the given argument.
     */
    @Query("select p from Person p where p.firstName like %?1% or p.lastName like %?1%")
    List<Person> getByFirstnameOrLastnameLike(String name);

    /**
     *
     * Find all persons with the given dob
     */
    @Query("select p from Person p where p.dateOfBirth = :dob")
    List<Person> getByDateOfBirth(@Param("dob") Date dob);

    /**
     * Returns all persons born ath the hospital with the given code
     */
    @Query("select p from Person p where p.hospital.code= :code")
    List<Person> getByHospital(@Param("code") String code);

    /**
     * Find all persons with the given hospital name.
     */
    @Query("select p from Person p where p.hospital.name = :name")
    List<Person> getByHospitalName(@Param("name") String name);

    /**
     *
     * Find all persons with the hospital name similar to the given hospital name.
     */
    @Query("select p from Person p where p.hospital.name like %?1%")
    List<Person> getByHospitalNameLike(String name);
}
