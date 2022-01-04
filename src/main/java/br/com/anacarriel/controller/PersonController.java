package br.com.anacarriel.controller;

import br.com.anacarriel.data.model.Person;
import br.com.anacarriel.data.vo.v1.PersonVO;
import br.com.anacarriel.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "person", description = "The Person Registration API")
public class PersonController {

    @Autowired
    private PersonServices service;

    @Operation(summary = "Find person by id", description = "Person search by ID format", tags = { "person" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
            @ApiResponse(responseCode = "404", description = "Person not found") })
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO findById(
            @Parameter(description="Id of the contact to be obtained. Cannot be empty.", required=true)
            @PathVariable("id") Long id) {
        PersonVO personVO = service.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @Operation(summary = "Find all persons", description = "Persons search format", tags = { "person" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
            @ApiResponse(responseCode = "404", description = "Persons not found")})
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<PersonVO> findAll() {

        List<PersonVO> persons =  service.findAll();
        persons
                .forEach(p -> p.add(
                        linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                    )
                );
        return persons;
    }

    @Operation(summary = "Create person", description = "Create person, inform: first and last name, etc", tags = { "person" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person created",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))) })
    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO create(@Parameter(description="Contact to add. Cannot null or empty.",
            required=true) @RequestBody PersonVO person) {
        PersonVO personVO = service.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @Operation(summary = "Update person", description = "Update person description", tags = { "person" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))) })
    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO update(@RequestBody PersonVO person) {

        PersonVO personVO = service.update(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @Operation(summary = "Delete person", description = "Delete person, inform id", tags = { "person" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}