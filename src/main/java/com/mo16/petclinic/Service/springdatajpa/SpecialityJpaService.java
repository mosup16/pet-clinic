package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.Service.SpecialityService;
import com.mo16.petclinic.model.Speciality;
import com.mo16.petclinic.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class SpecialityJpaService extends DataJpnAbstractService<Long, Speciality, SpecialityRepository>
        implements SpecialityService {
    @Autowired
    public SpecialityJpaService(SpecialityRepository repository) {
        this.repository = repository;
    }

}
