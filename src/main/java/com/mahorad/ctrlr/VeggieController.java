package com.mahorad.ctrlr;

import com.mahorad.model.Veggie;
import com.mahorad.service.VeggieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api/v1")
public class VeggieController {

    private static final Logger log = LoggerFactory.getLogger(VeggieController.class);
    private final VeggieService service;

    @Autowired
    public VeggieController(VeggieService service) {
        this.service = service;
    }

    @RequestMapping(value = "/veggies", method = GET)
    public ResponseEntity<List<Veggie>> getAll() {
        log.info(String.format("RECEIVED: GET \"%s\"", "/api/v1/veggies"));
        final List<Veggie> veggies = service.findAll();
        return new ResponseEntity<>(veggies, OK);
    }

    @RequestMapping(value = "/veggies/{id}", method = GET)
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info(String.format("RECEIVED: GET \"%s\"", "/api/v1/veggies/id"));
        final Veggie foundVeggie = service.findById(id);
        return foundVeggie != null
                ? new ResponseEntity<>(foundVeggie, OK)
                : new ResponseEntity<>("could not find id " + id, NOT_FOUND);
    }

    @RequestMapping(value = "/veggies/{id}", method = PUT)
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Veggie veggie) {
        log.info(String.format("RECEIVED: PUT \"%s\"", "/api/v1/veggies/id"));
        final Veggie updated = service.update(id, veggie);
        return updated != null
            ? new ResponseEntity<>(updated, OK)
            : new ResponseEntity<>("could not update", NOT_FOUND);
    }

    @RequestMapping(value = "/veggies/{id}", method = DELETE)
    public ResponseEntity<?> delete(@PathVariable String id) {
        log.info(String.format("RECEIVED: DELETE \"%s\"", "/api/v1/veggies/id"));
        final boolean deleted = service.delete(id);
        return deleted
            ? new ResponseEntity<>(NO_CONTENT)
            : new ResponseEntity<>("could not delete id " + id, NOT_FOUND);
    }

    @RequestMapping(value = "/veggies", method = POST)
    public ResponseEntity<?> create(@RequestBody Veggie veggie) {
        log.info(String.format("RECEIVED: POST \"%s\"", "/api/v1/veggies"));
        final Veggie created = service.create(veggie);
        return created != null
            ? new ResponseEntity<>("", CREATED)
            : new ResponseEntity<>("could not add", CONFLICT);
    }

}
