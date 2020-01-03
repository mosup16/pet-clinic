package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.model.Owner;
import com.mo16.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findByLastName() {
        String last_name = "smith";
        Owner smith = Owner.builder().id(1L).lastName(last_name).build();
        Set<Owner> owners = new HashSet<>();
        owners.add(smith);
        when(ownerRepository.findByLastName(any())).thenReturn(owners);
        Set<Owner> foundOwners = ownerJpaService.findByLastName(last_name);
        foundOwners.forEach(
                it -> {
                    assertEquals(last_name, it.getLastName());
                });
    }
}