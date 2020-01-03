package com.mo16.petclinic.Service.map;

import com.mo16.petclinic.Service.SpecialityService;
import com.mo16.petclinic.Service.map.Generation.BaseEntityMapLongIdGenerator;
import com.mo16.petclinic.model.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "mapJpaService"})
public class MapSpecialityService extends MapAbstractService<Long, Speciality> implements SpecialityService {

    private BaseEntityMapLongIdGenerator generator;

    public MapSpecialityService() {
    }

    @Autowired
    public MapSpecialityService(BaseEntityMapLongIdGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality save(Speciality object) {
        generator.IdGeneration(object, map);
        return super.save(object);
    }

    @Override
    public Speciality update(Speciality object) {
        return super.update(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteByID(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }
}
