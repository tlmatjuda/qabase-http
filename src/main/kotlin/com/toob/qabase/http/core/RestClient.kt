package com.toob.qabase.http.core

import io.qameta.allure.Step
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response

object RestClient {


    fun get(endpoint: String): Response = request("GET", endpoint)
    fun post(endpoint: String, body: Any): Response = request("POST", endpoint, body)
    fun put(endpoint: String, body: Any): Response = request("PUT", endpoint, body)
    fun delete(endpoint: String): Response = request("DELETE", endpoint)


    @Step("Sending {method} request to {endpoint}")
    private fun request(method: String, endpoint: String, body: Any? = null): Response {
        return RestAssured.given()
            .contentType(ContentType.JSON)
            .apply {
                if (body != null) {
                    body(body)
                }
            }
            .`when`()
            .request(method, endpoint)
            .then()
            .log().all()
            .extract().response()
    }
}