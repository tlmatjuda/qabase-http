package com.toob.qabase.http.todos

import com.toob.qabase.http.core.AllureExtensions
import com.toob.qabase.http.core.AllureExtensions.step
import com.toob.qabase.http.core.BaseSuite
import com.toob.qabase.http.core.RestClient
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import io.restassured.response.Response
import org.hamcrest.Matchers.greaterThan
import kotlin.test.Test
import kotlin.test.assertEquals

@Epic("JSONPlaceholder API Tests")
@Feature("TODOs API CRUD Operations")
class TodoApiTest : BaseSuite() {

    @Test
    @Story("Fetch all TODO items")
    fun `Fetch All Tasks`() {
        val response = step("Fetch All TODO Tasks") {
            RestClient.get("/todos")
        }

        assertHttpStatusCode(200, response)
        step("Validate response contains at least one TODO item") {
            response.then().body("size()", greaterThan(0))
        }

        attachApiResponse(response)
    }

    @Test
    @Story("Create a new TODO item")
    fun `Create New Task`() {
        val todo = Todo(userId = 1, title = "Implement Allure Reports with Kotlin and Spring Boot", completed = false)
        val requestBody = json.encodeToString(todo)

        val response = step("Creating a new TODO task") {
            RestClient.post("/todos", requestBody)
        }

        assertHttpStatusCode(201, response)
        attachApiResponse(response)
    }

    @Test
    @Story("Update an existing TODO item")
    fun `Update Task`() {
        val todo = Todo(userId = 1, title = "Updated Task", completed = false)
        val requestBody = json.encodeToString(todo)

        val response = step("Updating a TODO") {
            RestClient.put("/todos/1", requestBody)
        }

        assertHttpStatusCode(200, response)
        attachApiResponse(response)
    }

    @Test
    @Story("Delete a TODO item")
    fun `Delete Task`() {
        val response = step("Removing a TODO") {
            RestClient.delete("/todos/1")
        }

        assertHttpStatusCode(200, response)
        attachApiResponse(response)
    }


}