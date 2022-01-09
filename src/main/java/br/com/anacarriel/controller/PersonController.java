package br.com.anacarriel.controller;

import br.com.anacarriel.data.model.Person;
import br.com.anacarriel.data.vo.v1.PersonVO;
import br.com.anacarriel.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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

    @Operation(summary = "Disebla person by id", description = "Disebla by ID format", tags = { "person" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
            @ApiResponse(responseCode = "404", description = "Person not found") })
    @PatchMapping(value = "/disablePerson/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO disablePerson(
            @Parameter(description="Disable person by id.", required=true)
            @PathVariable("id") Long id, @RequestBody PersonVO person) {
        PersonVO personVO = service.disablePerson(id, person.getEnabled());
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @Operation(summary = "Find all persons", description = "Persons search format", tags = { "person" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
            @ApiResponse(responseCode = "404", description = "Persons not found")})
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<PagedResourcesAssembler<PersonVO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler assembler){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons =  service.findAll(pageable);
        persons
                .forEach(p -> p.add(
                        linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                    )
                );
        return new ResponseEntity<PagedResourcesAssembler<PersonVO>>(assembler.toModel(persons), HttpStatus.OK);
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