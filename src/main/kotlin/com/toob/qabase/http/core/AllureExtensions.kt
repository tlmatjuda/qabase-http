package com.toob.qabase.http.core

import io.qameta.allure.Allure
import io.restassured.http.ContentType
import io.restassured.response.Response

/**
 * This Kotlin object provides utility functions for ...
 * ...integrating Allure reporting into your API test automation framework.
 */
object AllureExtensions {

    /**
     * This function wraps a test step inside an Allure report entry.
     * It ensures that each test action is logged as a step in the Allure report.
     * It converts the action() into an Allure.ThrowableRunnable, which captures exceptions that occur inside the step.
     * It then calls Allure.step(...), passing the description and the wrapped action.
     *
     * @param description : String → A textual description of the step.
     * @param action: () -> T → A lambda function representing the step.
     */
    fun <T> step(description: String, action: () -> T): T {

        // Wrap the provided action inside an Allure ThrowableRunnable (captures errors)
        val throwableRunnable = Allure.ThrowableRunnable { action() }

        // Log the step description and execute the action
        return Allure.step( description, throwableRunnable)
    }


    /**
     * This function attaches the API response body to the Allure report.
     * It helps in debugging by logging the API response when a test runs.
     *
     * It calls Allure.addAttachment(...), which:
     *      Adds a title: "Response Body"
     *      Specifies the content type as JSON
     *      Reads the response body and attaches it to the report
     *
     * The JSON response will now be visibly attached in the Allure report.
     */
    fun attachResponse(response: Response) =
        Allure.addAttachment("Response Body",   // Attachment title in the report
            ContentType.JSON.name,  // Specify JSON format
            response.body.asString(),   // Extract the response body as a string
            "json") // File extension type
}