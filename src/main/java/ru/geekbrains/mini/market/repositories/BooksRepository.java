package ru.geekbrains.mini.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mini.market.entites.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
}
