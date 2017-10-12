package com.mahorad;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface VeggieRepository extends Repository<Veggie, String> {

    List<Veggie> findAll();

    Veggie findById(String id);

    void delete(String id);

    Veggie save(Veggie veggie);
}