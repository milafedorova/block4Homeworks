package ru.geekbrains.mini.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.mini.market.entites.Authors;
import ru.geekbrains.mini.market.entites.Books;
import ru.geekbrains.mini.market.repositories.AuthorsRepository;

import java.util.Optional;

@Service
public class AuthorsService {
    private AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Optional<Authors> getOneById(Long id) {
        return authorsRepository.findById(id);
    }

    public Authors save(Authors authors) { return authorsRepository.save(authors); }


}
