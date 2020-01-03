package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.Service.OwnerService;
import com.mo16.petclinic.model.Owner;
import com.mo16.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("springDataJpa")
public class OwnerJpaService extends DataJpnAbstractService<Long, Owner, OwnerRepository> implements OwnerService {

    @Autowired
    public OwnerJpaService(OwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Owner> findByLastName(String name) {
        return repository.findByLastName(name);
    }

}
