package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

public class DataJpnAbstractService<ID, Entity extends BaseEntity<ID>, Repository extends CrudRepository<Entity, ID>> {

    Repository repository;

    public DataJpnAbstractService() {
    }


    public Entity findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    public Set<Entity> findAll() {
        HashSet<Entity> owners = new HashSet<>();
        repository.findAll().forEach(owners::add);
        return owners;
    }

    public Entity save(Entity object) {
        return repository.save(object);
    }

    public Entity update(Entity object) {
        if (object != null) {
            ID id = object.getId();
            if (id != null) {
                if (repository.existsById(id)) {
                    repository.deleteById(id);
                    object = repository.save(object);
                }
            }
        }
        return object;
    }

    public void deleteByID(ID id) {
        repository.deleteById(id);
    }

    public void delete(Entity object) {
        repository.delete(object);
    }
}
