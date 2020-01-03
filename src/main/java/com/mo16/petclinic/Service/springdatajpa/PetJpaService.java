package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.Service.PetService;
import com.mo16.petclinic.model.Pet;
import com.mo16.petclinic.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class PetJpaService extends DataJpnAbstractService<Long, Pet, PetRepository> implements PetService {
    @Autowired
    public PetJpaService(PetRepository repository) {
        this.repository = repository;
    }

}
