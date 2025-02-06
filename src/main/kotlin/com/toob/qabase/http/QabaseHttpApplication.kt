package com.toob.qabase.http

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QabaseHttpApplication

fun main(args: Array<String>) {
	runApplication<QabaseHttpApplication>(*args)
}
