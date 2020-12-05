package com.fedorova.homework.Fedorova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8189/app/products
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Обработать запрос вида: GET /products/filtered?min_price=100. В результате должен вернуться список товаров, цена которых >= 100
    @GetMapping("/filtered")
    public List<Product> getAllCheapProducts(@RequestParam int min_price) {
        return productRepository.requestAllCheapProducts(min_price);
    }

    // Обработать запрос вида: GET /products/delete/1. В результате должен удалиться товар с соответствующим id
    @GetMapping("/delete/{id}")
    public String deleteProductByID(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ("Product "+ id + " is deleted");
    }

    // Попробуйте реализовать возможность изменения названия товара по id. Что-то вроде: /products/{id}/change_title...
    @GetMapping("/{id}/chqnge_title")
    public Product changeTitle(@PathVariable Long id, @RequestParam String new_title){
        Product product = productRepository.findById(id).get();
        product.setTitle(new_title);
        productRepository.save(product);
        return product;
    }

}
