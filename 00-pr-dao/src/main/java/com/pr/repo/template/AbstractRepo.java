package com.pr.repo.template;

/**
 * Generic template interface for all repository interfaces used in the project.
 * Created by iuliana.cosmina on 12/28/14.
 */
public interface AbstractRepo<AbstractEntity> {
    AbstractEntity findById(Integer id);

    void save(AbstractEntity entity);
}
