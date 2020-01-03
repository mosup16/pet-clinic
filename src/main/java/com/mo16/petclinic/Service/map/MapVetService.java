package com.mo16.petclinic.Service.map;

import com.mo16.petclinic.Service.VetService;
import com.mo16.petclinic.Service.map.Generation.BaseEntityMapLongIdGenerator;
import com.mo16.petclinic.model.Speciality;
import com.mo16.petclinic.model.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "mapJpaService"})
public class MapVetService extends MapAbstractService<Long, Vet> implements VetService {

    private BaseEntityMapLongIdGenerator generator;

    @Autowired
    public MapVetService(BaseEntityMapLongIdGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteByID(id);
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            generator.IdGeneration(object, map);
            Set<Speciality> specialities = object.getSpecialities();
            if (specialities != null) {
                if (specialities.size() != 0) {
                    return super.save(object);
                } else throw new RuntimeException(" -> Vet must have at least one speciality");
            } else throw new RuntimeException(" -> vet.getSpecialities() returned null");
        } else throw new RuntimeException(" -> (Vet object) is null can't save a null object");
    }

    @Override
    public Vet update(Vet object) {
        return super.update(object);
    }
}
