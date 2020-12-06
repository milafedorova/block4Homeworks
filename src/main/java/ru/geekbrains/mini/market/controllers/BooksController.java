package ru.geekbrains.mini.market.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mini.market.dto.BooksDto;
import ru.geekbrains.mini.market.entites.Books;
import ru.geekbrains.mini.market.exceptions.MarketError;
import ru.geekbrains.mini.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.mini.market.service.BooksService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Api("Set of endpoints for books")
public class BooksController {
    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a specific book by their identifier. 404 if does not exist.")
    public BooksDto getBookById(@ApiParam("Id of the book to be obtained. Cannot be empty.") @PathVariable Long id) {
        Books p = booksService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find book with id: " + id));
        return new BooksDto(p);
    }

    @PostMapping
    @ApiOperation("Creates a new book. If id != null, then throw bad request response")
    public ResponseEntity<?> createNewBook(@RequestBody Books p) {
        if (p.getId() != null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(booksService.save(p), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("Modify book")
    public ResponseEntity<?> modifyBook(@RequestBody Books p) {
        if (p.getId() == null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be not null for new entity"), HttpStatus.BAD_REQUEST);
        }
        if (!booksService.existsById(p.getId())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product with id: " + p.getId() + " doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(booksService.save(p), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete book")
    public void deleteById(@ApiParam("Id of the book") @PathVariable Long id) {
        booksService.deleteById(id);
    }
}
