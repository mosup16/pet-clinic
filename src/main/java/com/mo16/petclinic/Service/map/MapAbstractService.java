package com.mo16.petclinic.Service.map;

import com.mo16.petclinic.Service.CrudService;
import com.mo16.petclinic.model.BaseEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapAbstractService<ID, T extends BaseEntity> implements Serializable , CrudService<ID, T> {

    protected Map<ID, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(ID id) {
        return map.get(id);
    }

    public T save(T object) {
        if (object == null) throw new RuntimeException(" -> There is no Entity to be Saved Entity is Null");
        Object id = object.getId();
        if (id != null) {
            T entity = findById((ID) id);
            if (entity != null) {
                object = entity;
            }
        }
        map.put((ID) object.getId(), object);
        return object;
    }

    public T update(T object) {
        if (object.getId() == null)
            throw new RuntimeException(" -> The Id of the desired entity to be updated is missing");
        map.remove(object.getId());
        map.put((ID) object.getId(), object);
        return object;
    }

    public void deleteByID(ID id) {
        map.remove(id);
    }

    public void delete(T object) {
        map.remove(object.getId());
    }


}
