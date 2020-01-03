package com.mo16.petclinic.Service;

import com.mo16.petclinic.model.Owner;

import java.util.Set;


public interface OwnerService extends CrudService<Long, Owner> {
    Set<Owner> findByLastName(String name);
}
