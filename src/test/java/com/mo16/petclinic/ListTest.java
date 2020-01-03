package com.mo16.petclinic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest {
    @Test
    public void testSet() {
        List<SimpleObject> objects = new ArrayList<>();
        objects.add(null);
        Assertions.assertNull(objects.get(0));
    }

    @Test
    public void mapTest() {
        Map<Long, SimpleObject> map = new HashMap<>();
        map.put(1L, null);
        map.put(1L, new SimpleObject());
        assert (map.get(1L) != null);
    }

    class SimpleObject {
        Long id;
        String name;
    }
}
