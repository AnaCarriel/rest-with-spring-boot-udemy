package br.com.anacarriel.controller;

import br.com.anacarriel.data.model.Person;
import br.com.anacarriel.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}")
    public br.com.anacarriel.data.vo.PersonVO findById(@PathVariable(value = "id") Long id) {
        return services.findeById(id);
    }

    @GetMapping
    public List<br.com.anacarriel.data.vo.PersonVO> findAll () {
        return services.findAll();
    }

    @PostMapping
    public Person create (@RequestBody Person person) {
        return services.create(person);
    }

    @PutMapping
    public Person update (@RequestBody br.com.anacarriel.data.vo.PersonVO person) {
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}