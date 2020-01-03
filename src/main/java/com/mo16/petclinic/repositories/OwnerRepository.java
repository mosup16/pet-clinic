package com.mo16.petclinic.repositories;

import com.mo16.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Set<Owner> findByLastName(String lastName);

}
