package com.pr.repo;

import com.pr.base.AbstractEntity;
import com.pr.repo.template.AbstractRepo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
public class HibernateAbstractRepo implements AbstractRepo<AbstractEntity> {

    private SessionFactory sessionFactory;

    /**
     * Creates a new Hibernate account manager.
     *
     * @param sessionFactory the Hibernate session factory
     */
    @Autowired
    public HibernateAbstractRepo(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public AbstractEntity findById(Integer id) {
        return null;
    }

    @Override
    public void save(AbstractEntity abstractEntity) {

    }
}
