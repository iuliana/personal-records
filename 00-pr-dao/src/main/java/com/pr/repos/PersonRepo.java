package com.pr.repos;

import com.pr.ents.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
     * Returns all persons with the given name as firstname or lastname.
     */
    @Query("select p from Person p where p.firstName = :name or p.lastName = :name")
    List<Person> getByFirstnameOrLastname(@Param("name") String name);

    /**
     * Returns all persons with firstname or lastname similar to the given argument.
     */
    @Query("select p from Person p where p.firstName like %?1% or p.lastName like %?1%")
    List<Person> getByFirstnameOrLastnameLike(String srg);
}
