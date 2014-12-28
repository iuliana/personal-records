package com.pr.repos;

import com.pr.ents.Account;
import com.pr.ents.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
public interface AccountRepo extends JpaRepository<Account, Long>{

    /**
     * Find all the accounts for the given person.
     */
    @Query("select h from Hospital h where h.code = :code")
    List<Account> findByPerson(Person person);
}
