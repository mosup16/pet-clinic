package com.mo16.petclinic.Service.map.Generation;

import com.mo16.petclinic.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BaseEntityMapLongIdGenerator {
    private MapIdGenerator mapIdGenerator;

    @Autowired
    public BaseEntityMapLongIdGenerator(@Qualifier("mapLongIdGenerator") MapIdGenerator mapIdGenerator) {
        this.mapIdGenerator = mapIdGenerator;
    }

    public void IdGeneration(BaseEntity object, Map map) {
        if (object == null || map == null)
            throw new RuntimeException(" -> Can't generate Id values for a null object or a null map");
        if (object.getId() == null) object.setId((Long) mapIdGenerator.Generate(map));
    }

}
