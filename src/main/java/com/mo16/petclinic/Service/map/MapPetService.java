package com.mo16.petclinic.Service.map;

import com.mo16.petclinic.Service.PetService;
import com.mo16.petclinic.Service.PetTypeService;
import com.mo16.petclinic.Service.map.Generation.BaseEntityMapLongIdGenerator;
import com.mo16.petclinic.model.Pet;
import com.mo16.petclinic.model.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "mapJpaService"})
public class MapPetService extends MapAbstractService<Long, Pet> implements PetService {

    private BaseEntityMapLongIdGenerator generator;
    private PetTypeService petTypeService;

    @Autowired
    public MapPetService(BaseEntityMapLongIdGenerator generator, PetTypeService petTypeService) {
        this.generator = generator;
        this.petTypeService = petTypeService;
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteByID(id);
    }

    @Override
    public Pet save(Pet object) {
        if (object != null) {
            if (object.getPetType() != null) {
                PetType savedPetType = petTypeService.save(object.getPetType());
                object.setPetType(savedPetType);
            } else throw new RuntimeException(" -> Pet must have a type petType should not be null");
        } else throw new RuntimeException(" -> cant save a null object pet should not be null to be saved");


        generator.IdGeneration(object, map);
        return super.save(object);
    }

    public Pet update(Pet object) {
        return super.update(object);
    }

}
