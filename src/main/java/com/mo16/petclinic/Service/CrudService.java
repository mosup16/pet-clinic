package com.mo16.petclinic.Service;

import java.util.Set;

public interface CrudService<ID, T> {
    T findById(ID id);

    Set<T> findAll();

    T save(T object);

    T update(T object);

    void deleteByID(ID id);

    void delete(T object);
}
