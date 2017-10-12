package com.mahorad.service;

import com.mahorad.model.Veggie;
import com.mahorad.model.IVeggieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class VeggieService implements IVeggieService {

    private final IVeggieRepository repository;

    @Autowired
    public VeggieService(IVeggieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Veggie create(Veggie veggie) {
        if (veggie == null || exists(veggie.getId()))
            return null;
        return repository.save(veggie);
    }

    @Override
    public List<Veggie> findAll() {
        return repository.findAll();
    }

    @Override
    public Veggie findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Veggie update(String id, Veggie veggie) {
        if (veggie == null || !exists(id))
            return null;
        Veggie update = new Veggie(id, veggie.getName(), veggie.getFee());
        return repository.save(update);
    }

    public boolean delete(String id) {
        if (!exists(id))
            return false;
        repository.delete(id);
        return true;
    }

    private boolean exists(String id) {
        return findById(id) != null;
    }

}
