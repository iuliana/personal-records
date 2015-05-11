package com.pr.repos;

import com.pr.ents.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by iuliana.cosmina on 12/28/14.
 */
public interface HospitalRepo extends JpaRepository<Hospital, Long> {

    /**
     * Find the hospital with the given code.
     */
    @Query("select h from Hospital h where h.code = :code")
    Hospital findByCode(@Param("code") String code);

    /**
     * Find the hospitals with code which contains the given digit sequence.
     */
    @Query("select h from Hospital h where h.code like %?1%")
    List<Hospital> findByCodeLike(String code);
}
