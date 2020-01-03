package com.mo16.petclinic.Service.map;

import com.mo16.petclinic.Service.PetTypeService;
import com.mo16.petclinic.Service.map.Generation.BaseEntityMapLongIdGenerator;
import com.mo16.petclinic.model.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "mapJpaService"})
public class MapPetTypeService extends MapAbstractService<Long, PetType> implements PetTypeService {

    private BaseEntityMapLongIdGenerator generator;

    @Autowired
    public MapPetTypeService(BaseEntityMapLongIdGenerator generator) {
        this.generator = generator;
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteByID(id);
    }

    @Override
    public PetType save(PetType object) {
        // ensures that the Pet Type is unique
        Set<PetType> correspondingPetTypes = findByName(object.getName());
        if (correspondingPetTypes.size() == 0) {
            generator.IdGeneration(object, map);
            PetType savedPetType = super.save(object);
            return savedPetType;
        } else {
            return new ArrayList<PetType>(correspondingPetTypes).get(0);
        }
    }

    @Override
    public PetType update(PetType object) {
        return super.update(object);
    }

    /**
     * @param name
     * @return
     */
    public Set<PetType> findByName(String name) {
        return map
                .values()
                .stream()
                .filter(it -> (it.getName().equals(name)))
                .collect(Collectors.toSet())
                ;
    }
}
