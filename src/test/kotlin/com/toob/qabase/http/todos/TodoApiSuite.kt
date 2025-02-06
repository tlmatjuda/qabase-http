package com.toob.qabase.http.todos

import com.toob.qabase.http.core.AllureExtensions
import com.toob.qabase.http.core.AllureExtensions.step
import com.toob.qabase.http.core.BaseSuite
import com.toob.qabase.http.core.RestClientSupport
import org.hamcrest.Matchers.greaterThan
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

class TodoApiSuite : BaseSuite() {

    @Test
    @DisplayName("Fetch All Tasks")
    fun fetchAllTodos() {
        val response = step("Send GET request to fetch all TODOs") {
            RestClientSupport.get("/todos")
        }

        step("Verify response status code") {
            assertEquals(200, response.statusCode)
        }

        step("Validate response contains at least one TODO item") {
            response.then().body("size()", greaterThan(0))
        }

        step("Attach response to Allure report") {
            AllureExtensions.attachResponse(response)
        }
    }


}