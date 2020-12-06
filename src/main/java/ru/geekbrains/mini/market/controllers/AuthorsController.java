package ru.geekbrains.mini.market.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mini.market.dto.AuthorsDto;
import ru.geekbrains.mini.market.dto.AuthorsShortDto;
import ru.geekbrains.mini.market.entites.Authors;
import ru.geekbrains.mini.market.entites.Books;
import ru.geekbrains.mini.market.exceptions.MarketError;
import ru.geekbrains.mini.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.mini.market.service.AuthorsService;

@RestController
@RequestMapping("/api/v1/authors")
@Api("Set of endpoints for authors")
public class AuthorsController {
    private AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a specific authors by their identifier. 404 if does not exist.")
    public AuthorsDto getAuthorsById(@PathVariable Long id) {
        Authors authors = authorsService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find author with id: " + id));
        return new AuthorsDto(authors);
    }

    // 3. Через REST API дайте возможность запрашивать автора по id со следующей структурой:
    // {
    //   "id": ...,
    //   "name": ...,
    //   "booksCount": ... // количество написанных книг
    // }

    @GetMapping("/count/{id}")
    @ApiOperation("Returns a specific authors by their identifier with count of books. 404 if does not exist.")
    public AuthorsShortDto getCountOfBooksByAuthor(@PathVariable Long id){
        Authors authors = authorsService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find author with id: " + id));
        return new AuthorsShortDto(authors);
    }

    @PostMapping("/create")
    @ApiOperation("Create new authors. 400 if ID not null.")
    public ResponseEntity<?> createNewAuthor(@RequestBody Authors a) {
        if (a.getId() != null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authorsService.save(a), HttpStatus.CREATED);
    }
}
