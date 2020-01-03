package com.mo16.petclinic.Service.springdatajpa;

import com.mo16.petclinic.model.BaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataJpnAbstractServiceTest {

    private String last_name = "smith";
    private Long id = 1L;

    Set<BaseEntity> entities;
    BaseEntity entity;
    @Mock
    CrudRepository repository;

    @InjectMocks
    DataJpnAbstractService dataJpnAbstractService;

    @BeforeEach
    void setUp() {
        entities = new HashSet<>();
        entity = BaseEntity.builder().id(id).build();
        entities.add(entity);
    }

    @Test
    void findById() {
        when(repository.findById(any()).orElse(null)).thenReturn(Optional.of(entity));
        BaseEntity entity = dataJpnAbstractService.findById(id);
        assert id.equals(entity.getId());
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(any()).orElse(null)).thenReturn(Optional.empty());
        BaseEntity e = dataJpnAbstractService.findById(id);
        assertNull(e);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(entities);
        Set all = dataJpnAbstractService.findAll();
        all.forEach(it -> {
            BaseEntity e = (BaseEntity) it;
            assert id.equals(e.getId());
        });
        assert 1 == all.size();
    }

    @Test
    void save() {
        when(repository.save(any())).thenReturn(entity);
        BaseEntity savedEntity = dataJpnAbstractService.save(entity);
        assertNotNull(savedEntity);
        verify(repository, times(1)).save(any());
    }

    @Test
    void update() {
        when(repository.save(any())).thenReturn(entity);
        when(repository.existsById(any())).thenReturn(true);
        BaseEntity updatedEntity = dataJpnAbstractService.update(entity);
        assertNotNull(updatedEntity);
        verify(repository, times(1)).save(any());
        verify(repository, times(1)).existsById(any());
        verify(repository, times(1)).deleteById(any());
    }

    @Test
    void deleteByID() {
        dataJpnAbstractService.deleteByID(entity.getId());
        verify(repository, times(1)).deleteById(any());
    }

    @Test
    void delete() {
        dataJpnAbstractService.delete(entity);
        verify(repository, times(1)).delete(any());
    }
}