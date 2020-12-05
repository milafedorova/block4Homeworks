package com.fedorova.homework.Fedorova;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Запрос для задания 1 из домашнего задания
    @Query("select p from Product p where p.price < ?1")
    List<Product> requestAllCheapProducts(int price);




}

