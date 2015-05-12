package com.pr.service;

import com.pr.ents.Hospital;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/11/15.
 * Description: Interface that describes behaviour of a hospital service class
 */
public interface HospitalManager extends BaseManager<Hospital> {

    @Transactional(readOnly = true)
    Hospital findByCode(String code);

    @Transactional(readOnly = true)
    List<Hospital> findByCodeLike(String code);
}
