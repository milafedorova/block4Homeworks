package ru.geekbrains.mini.market.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class RestAssuredTests {
    /*
        {
            "id": 1,
            "title": "Milk",
            "price": 95,
            "categoryTitle": "Food"
        }
     */
    @Test
    public void simpleTest() {
        when()
                .get("http://localhost:8189/market/api/v1/products/1")
                .then()
                .statusCode(200)
                .and()
                .body("id", equalTo(1))
                .body("title", equalTo("Milk"));
    }


    @Test
    public void getAllProductsTest() {
        when()
                .get("http://localhost:8189/market/api/v1/products")
                .then()
                .statusCode(200)
                .and()
                .body("$", hasSize(5))
                .body("[0].title", equalTo("Milk"));
    }


    @Test
    public void testPostMappingProduct() {
        // Product p = new Product(null, "Calculator", 1000, "Electronic");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8189)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        Map<String, String> productMap = new HashMap<>();
        productMap.put("title", "Calculator");
        productMap.put("price", "1000");
        productMap.put("categoryTitle", "Electronic");

        given()
                .spec(requestSpecification)
                .body(productMap)
                .when()
                .post("market/api/v1/products")
                .then()
                .log().ifValidationFails(LogDetail.BODY)
                .body("title", equalTo("Calculator"));
    }

    @Test
    public void testPutMappingProduct() {
        Product p = new Product((long) 2, "Calculator", 1000, "Electronic");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8189)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        given()
                .spec(requestSpecification)
                .body(p)
                .when()
                .put("market/api/v1/products")
                .then()
                .log().ifValidationFails(LogDetail.BODY)
                .body("title", equalTo("Calculator"));
    }

    @Test
    public void testDeleteMappingProduct(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8189)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        given()
                .spec(requestSpecification)
                .when()
                .delete("market/api/v1/products/1")
                .then()
                .body("$", hasSize(4));
    }

    @Test
    public void testIdMustBeNullForNewEntity(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8189)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        Map<String, String> productMap = new HashMap<>();
        productMap.put("id", "3");
        productMap.put("title", "Calculator");
        productMap.put("price", "1000");
        productMap.put("categoryTitle", "Electronic");

        given()
                .spec(requestSpecification)
                .body(productMap)
                .when()
                .post("market/api/v1/products")
                .then()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(400)
                .body("message", equalTo("Id must be null for new entity"));
    }

    @Test
    public void testIdMustBeNotNullPutMapping(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8189)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        Map<String, String> productMap = new HashMap<>();
        productMap.put("title", "Calculator");
        productMap.put("price", "1000");
        productMap.put("categoryTitle", "Electronic");

        given()
                .spec(requestSpecification)
                .body(productMap)
                .when()
                .put("market/api/v1/products")
                .then()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(400)
                .body("message", equalTo("Id must be not null for new entity"));
    }

    @Test
    public void testProductIdDoesNotExistPutMapping(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8189)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        Map<String, String> productMap = new HashMap<>();
        productMap.put("id", "1000");
        productMap.put("title", "Calculator");
        productMap.put("price", "1000");
        productMap.put("categoryTitle", "Electronic");

        given()
                .spec(requestSpecification)
                .body(productMap)
                .when()
                .put("market/api/v1/products")
                .then()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(400)
                .body("message", equalTo("Product with id: 1000 doesn't exist"));
    }

}
