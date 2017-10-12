package com.mahorad.model;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface IVeggieRepository extends Repository<Veggie, String> {

    List<Veggie> findAll();

    Veggie findById(String id);

    void delete(String id);

    Veggie save(Veggie veggie);
}