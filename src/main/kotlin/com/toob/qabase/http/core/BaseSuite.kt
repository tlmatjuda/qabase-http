package com.toob.qabase.http.core

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Allows non-static @BeforeAll
abstract class BaseSuite {

    @BeforeAll
    fun setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"
    }
}