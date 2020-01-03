package com.mo16.petclinic.Service.map.Generation;

public interface MapIdGenerator<Id, DataSource> {
    Id Generate(DataSource dataSource);
}
