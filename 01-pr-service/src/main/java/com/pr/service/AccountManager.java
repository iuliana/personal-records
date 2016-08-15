package com.pr.service;

import com.pr.ents.Account;
import com.pr.ents.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/11/15.
 */
public interface AccountManager extends BaseManager<Account>{

    @Transactional(readOnly = true)
    List<Account> findByPerson(Person person);
}
