package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.Service.VetService;
import com.mo16.petclinic.model.Vet;
import com.mo16.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class VetJpaService extends DataJpnAbstractService<Long, Vet, VetRepository> implements VetService {
    @Autowired
    public VetJpaService(VetRepository repository) {
        this.repository = repository;
    }

}
