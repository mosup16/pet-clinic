package com.mo16.petclinic.Service.map;

import com.mo16.petclinic.Service.VisitService;
import com.mo16.petclinic.Service.map.Generation.BaseEntityMapLongIdGenerator;
import com.mo16.petclinic.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "mapJpaService"})
public class MapVisitService extends MapAbstractService<Long, Visit> implements VisitService {


    private BaseEntityMapLongIdGenerator generator;

    @Autowired
    public MapVisitService(BaseEntityMapLongIdGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteByID(id);
    }

    @Override
    public Visit update(Visit object) {
        return null;
    }

    @Override
    public Visit save(Visit object) {
        if (object == null || object.getPet() == null || object.getPet().getId() == null
                || object.getPet().getPetOwner() == null
                || object.getPet().getPetOwner().getId() == null) throw new RuntimeException("invalid Visit");
        generator.IdGeneration(object, map);
        return super.save(object);
    }
}
