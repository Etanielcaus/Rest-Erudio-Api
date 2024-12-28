package com.cursoudemy.PostGresConnectionSpring.mapper;

import org.modelmapper.ModelMapper;

import com.cursoudemy.PostGresConnectionSpring.data.v2.PersonVOV2;
import com.cursoudemy.PostGresConnectionSpring.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperDTO {
    private static ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(Person.class, PersonVOV2.class)
        .addMapping(Person::getId, PersonVOV2::setKey);
        mapper.createTypeMap(PersonVOV2.class, Person.class)
        .addMapping(PersonVOV2::getKey, Person::setId);
    }

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}
