package com.pr.repos;

import com.pr.ents.IdentityCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
public interface IdentityCardRepo extends JpaRepository<IdentityCard, Long> {

    /**
     * Find the hospital with the given code.
     */
    @Query("select i from IdentityCard i where i.pnc = :pnc")
    IdentityCard findByPnc(@Param("pnc") String pnc);
}
