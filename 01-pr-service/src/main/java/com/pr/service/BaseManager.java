package com.pr.service;

import com.pr.base.AbstractEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/11/15.
 * Description: Basic interface describing the common behaviour of service implementations in this application
 */
@Transactional
public interface BaseManager<E extends AbstractEntity> {

    @Transactional(readOnly = true)
    List<E> findAll();

    @Transactional(readOnly = true)
    E findById(Long id);

    E save(E e);

    void delete(E e);

    void deleteById(Long id);
}
