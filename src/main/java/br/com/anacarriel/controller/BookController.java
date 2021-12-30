package br.com.anacarriel.controller;

import br.com.anacarriel.data.model.Book;
import br.com.anacarriel.data.vo.v1.BookVO;
import br.com.anacarriel.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "book", description = "The Book Registration API")
public class BookController {

    @Autowired
    private BookServices service;

    @Operation(summary = "Find book by id", description = "Book search by ID format", tags = { "book" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))),
            @ApiResponse(responseCode = "404", description = "Book not found") })
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO findById(
            @Parameter(description="Id of the contact to be obtained. Cannot be empty.", required=true)
            @PathVariable("id") Long id) {
        BookVO bookVO = service.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    @Operation(summary = "Find all books", description = "Books search format", tags = { "book" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))),
            @ApiResponse(responseCode = "404", description = "Persons not found")})
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<BookVO> findAll() {

        List<BookVO> books =  service.findAll();
        books
                .forEach(p -> p.add(
                                linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()
                        )
                );
        return books;
    }

    @Operation(summary = "Create book", description = "Create book, inform: first and last name, etc", tags = { "book" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book created",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))) })
    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO create(@Parameter(description="Contact to add. Cannot null or empty.",
            required=true) @RequestBody BookVO book) {
        BookVO bookVO = service.create(book);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @Operation(summary = "Update book", description = "Update book description", tags = { "book" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))) })
    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO update(@RequestBody BookVO book) {

        BookVO bookVO = service.update(book);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @Operation(summary = "Delete book", description = "Delete book, inform id", tags = { "book" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
