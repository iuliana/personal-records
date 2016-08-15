package com.pr.service;

import com.pr.ents.Account;
import com.pr.ents.Person;
import com.pr.repos.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/11/15.
 * Description: Service class that manages account entities
 */
@Service
public class AccountManagerImpl implements AccountManager {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepo.findOne(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public void delete(Account account) {
        accountRepo.delete(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepo.delete(id);
    }

    @Override
    public List<Account> findByPerson(Person person) {
        return accountRepo.findByPerson(person);
    }
}
