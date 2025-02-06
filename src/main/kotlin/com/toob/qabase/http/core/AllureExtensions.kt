package com.toob.qabase.http.core

import io.qameta.allure.Allure
import io.restassured.http.ContentType
import io.restassured.response.Response

object AllureExtensions {

    fun <T> step(description: String, action: () -> T): T {
        val throwableRunnable = Allure.ThrowableRunnable { action() }
        return Allure.step( description, throwableRunnable)
    }

    fun attachResponse(response: Response) =
        Allure.addAttachment("Response Body",
            ContentType.JSON.name,
            response.body.asString(), "json")
}