package com.pr.service;

import com.pr.ents.Hospital;
import com.pr.repos.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/11/15.
 *  * Description: Service class that manages Hospital entities
 */
@Service
public class HospitalManagerImpl implements HospitalManager {

    @Autowired
    public HospitalRepo hospitalRepo;

    @Override
    public List<Hospital> findAll() {
        return hospitalRepo.findAll();
    }

    @Override
    public Hospital findById(Long id) {
        return hospitalRepo.findOne(id);
    }

    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepo.save(hospital);
    }

    @Override
    public void delete(Hospital hospital) {
        hospitalRepo.delete(hospital);
    }

    @Override
    public void deleteById(Long id) {
        hospitalRepo.delete(id);
    }

    @Override
    public Hospital findByCode(String code) {
        return hospitalRepo.findByCode(code);
    }

    @Override
    public List<Hospital> findByCodeLike(String code) {
        return hospitalRepo.findByCodeLike(code);
    }
}
