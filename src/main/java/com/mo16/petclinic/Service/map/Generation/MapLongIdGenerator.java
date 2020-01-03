package com.mo16.petclinic.Service.map.Generation;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MapLongIdGenerator implements MapIdGenerator<Long, Map> {
    @Override
    public Long Generate(Map map) {
        return map.keySet().size() + 1L;
    }
}
