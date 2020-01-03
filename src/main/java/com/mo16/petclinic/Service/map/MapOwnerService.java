package com.mo16.petclinic.Service.map;

import com.mo16.petclinic.Service.OwnerService;
import com.mo16.petclinic.Service.map.Generation.BaseEntityMapLongIdGenerator;
import com.mo16.petclinic.model.Owner;
import com.mo16.petclinic.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "mapJpaService"})
public class MapOwnerService extends MapAbstractService<Long, Owner> implements OwnerService {

    private BaseEntityMapLongIdGenerator generator;
    private MapPetService petService;

    @Autowired
    public MapOwnerService(BaseEntityMapLongIdGenerator generator, MapPetService petService) {
        this.generator = generator;
        this.petService = petService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteByID(id);
    }

    @Override
    public Owner save(Owner object) {
        generator.IdGeneration(object, map);
        prepare(object);
        return super.save(object);
    }

    public Owner update(Owner object) {
        return super.update(object);
    }


    private void prepare(Owner object) {
        if (object != null) {
            Set<Pet> pets = object.getPets();
            if (pets != null) {
                if (!pets.isEmpty()) {
                    pets.forEach(pet -> {
                        if (pet != null) {
                            System.out.println(pet.getName());
                            Pet savedPet = petService.save(pet);
                            pet.setId(savedPet.getId());
                        }// else ignore
                    });
                } else throw new RuntimeException(" -> Owner Should has pets to be persisted");
            } else throw new RuntimeException(" -> Owner.getPets returned null");
        } else throw new RuntimeException(" -> Owner Object can't be null");

    }

    @Override
    public Set<Owner> findByLastName(String name) {
        return map
                .values()
                .stream()
                .filter(it -> it.getLastName().equals(name))
                .collect(Collectors.toSet());
    }
}
