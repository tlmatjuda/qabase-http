package com.toob.qabase.http.core

import com.toob.qabase.http.core.AllureExtensions.step
import io.restassured.RestAssured
import io.restassured.response.Response
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Allows non-static @BeforeAll
abstract class BaseSuite {

    val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    @BeforeAll
    fun setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"
    }

    fun attachApiResponse(response: Response) {
        step("Attach API Response") {
            AllureExtensions.attachResponse(response)
        }
    }

    fun assertHttpStatusCode(expectedCode: Int, response: Response) {
        step("Verify response status code") {
            assertEquals( expectedCode, response.statusCode)
        }
    }
}