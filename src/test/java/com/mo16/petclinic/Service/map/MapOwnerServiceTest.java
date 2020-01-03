package com.mo16.petclinic.Service.map;

import com.mo16.petclinic.Service.map.Generation.BaseEntityMapLongIdGenerator;
import com.mo16.petclinic.Service.map.Generation.MapLongIdGenerator;
import com.mo16.petclinic.model.Owner;
import com.mo16.petclinic.model.Pet;
import com.mo16.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MapOwnerServiceTest {
    Owner savedOwner;
    MapOwnerService ownerService;
    private int ownersSize;

    @BeforeEach
    void setUp() {
        BaseEntityMapLongIdGenerator generator = new BaseEntityMapLongIdGenerator(new MapLongIdGenerator());
        BaseEntityMapLongIdGenerator petTypeGenerator = new BaseEntityMapLongIdGenerator(new MapLongIdGenerator());
        BaseEntityMapLongIdGenerator petGenerator = new BaseEntityMapLongIdGenerator(new MapLongIdGenerator());

        MapPetTypeService petTypeService = new MapPetTypeService(petTypeGenerator);
        MapPetService petService = new MapPetService(petGenerator, petTypeService);
        ownerService = new MapOwnerService(generator, petService);
        Owner owner = Owner
                .builder()
                .firstName("Mo")
                .lastName("Em")
                .build();
        PetType dog = PetType
                .builder()
                .name("dog")
                .build();
        Pet max = Pet
                .builder()
                .name("max")
                .petType(dog)
                .build();
        owner.getPets().add(max);
        ownersSize = 1;
        savedOwner = ownerService.save(owner);
    }

    @Test
    void findById() {
        Owner ownerById = ownerService.findById(savedOwner.getId());
        assert savedOwner.getId().equals(ownerById.getId());
    }

    @Test
    void findAll() {
        Set<Owner> allOwners = ownerService.findAll();
        assert allOwners.size() == ownersSize;
    }

    @Test
    void delete() {
        ownerService.delete(savedOwner);
        assert 0 == ownerService.findAll().size();
    }

    @Test
    void deleteByID() {
        ownerService.deleteByID(savedOwner.getId());
        assert 0 == ownerService.findAll().size();
    }

    @Test
    void save() {
        Owner owner1 = Owner
                .builder()
                .firstName("Om")
                .lastName("Ema")
                .build();
        PetType dog = PetType
                .builder()
                .name("dog")
                .build();
        Pet max = Pet
                .builder()
                .name("max")
                .petType(dog)
                .build();
        owner1.getPets().add(max);

        ownerService.save(owner1);
        assert ownerService.findAll().size() == 2;
    }

    @Test
    void savePreparation() {
        Owner owner1 = Owner
                .builder()
                .firstName("Om")
                .lastName("Ema")
                .build();
        PetType dog = PetType
                .builder()
                .name("dog")
                .build();
        Pet max = Pet
                .builder()
                .name("max")
                .petType(dog)
                .build();
        // owner1.getPets().add(max);
        assertThrows(RuntimeException.class, () -> ownerService.save(owner1), " -> Owner Should has pets to be persisted");
        owner1.setPets(null);
        assertThrows(RuntimeException.class, () -> ownerService.save(owner1), " -> Owner.getPets returned null");
        assertThrows(RuntimeException.class, () -> ownerService.save(null), " -> Can't generate Id values for a null object or a null map");
    }

    @Test
    void update() {
        Owner owner1 = Owner
                .builder()
                .id(1L)
                .firstName("Om")
                .lastName("Ema")
                .build();
        PetType dog = PetType
                .builder()
                .name("dog")
                .build();
        Pet max = Pet
                .builder()
                .name("max")
                .petType(dog)
                .build();
        owner1.getPets().add(max);

        ownerService.update(owner1);

        assert ownerService.findAll().size() == ownersSize;
        assert ownerService.findByLastName("Ema").size() != 0;
        assert ownerService.findByLastName("Em").size() == 0;
    }

    @Test
    void findByLastName() {
        Set<Owner> byLastName = ownerService.findByLastName("Em");
        assert byLastName.size() != 0;
    }
}