package br.com.anacarriel.controller;

import br.com.anacarriel.data.model.Person;
import br.com.anacarriel.data.vo.v1.PersonVO;
import br.com.anacarriel.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}")
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return services.findeById(id);
    }

    @GetMapping
    public List<PersonVO> findAll () {
        return services.findAll();
    }

    @PostMapping
    public Person create (@RequestBody Person person) {
        return services.create(person);
    }

    @PutMapping
    public Person update (@RequestBody PersonVO person) {
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}