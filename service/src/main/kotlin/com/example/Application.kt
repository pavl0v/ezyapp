package com.example

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
	info = Info(
		title = "ezyapp",
		version = "0.0",
		description = "ezyapp API description"
	)
)
object Application {
	@JvmStatic
	fun main(args: Array<String>) {
		run(*args)
	}
}
