package ru.geekbrains.mini.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mini.market.entites.Authors;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Long> {
}
