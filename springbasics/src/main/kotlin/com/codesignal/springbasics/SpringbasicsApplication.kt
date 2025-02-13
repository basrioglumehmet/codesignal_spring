package com.codesignal.springbasics

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class SpringbasicsApplication(private val restTemplate: RestTemplate) {

	@PostConstruct
	fun fetchJsonData() {
		val url = "https://jsonplaceholder.typicode.com/todos/1"
		val response = restTemplate.getForObject(url, String::class.java)
		println("API Response: $response")
	}
}

fun main(args: Array<String>) {
	runApplication<SpringbasicsApplication>(*args)
}
