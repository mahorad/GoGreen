package com.mahorad;

import java.util.List;

public interface IVeggieService {

    Veggie create(Veggie veggie);

    List<Veggie> findAll();

    Veggie findById(String id);

    Veggie update(String id, Veggie veggie);

    boolean delete(String id);

}
