package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.Service.VisitService;
import com.mo16.petclinic.model.Visit;
import com.mo16.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class VisitJpaService extends DataJpnAbstractService<Long, Visit, VisitRepository> implements VisitService {
    @Autowired
    public VisitJpaService(VisitRepository repository) {
        this.repository = repository;
    }

}
