package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.Service.PetTypeService;
import com.mo16.petclinic.model.PetType;
import com.mo16.petclinic.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class PetTypeJpaService extends DataJpnAbstractService<Long, PetType, PetTypeRepository> implements PetTypeService {
    @Autowired
    public PetTypeJpaService(PetTypeRepository repository) {
        this.repository = repository;
    }

}
