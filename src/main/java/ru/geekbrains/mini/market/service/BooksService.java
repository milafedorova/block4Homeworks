package ru.geekbrains.mini.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.mini.market.entites.Books;
import ru.geekbrains.mini.market.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Optional<Books> getOneById(Long id) {
        return booksRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return booksRepository.existsById(id);
    }

    public Books save(Books books) {
        return booksRepository.save(books);
    }

    public void deleteById(Long id) {
        booksRepository.deleteById(id);
    }
}
