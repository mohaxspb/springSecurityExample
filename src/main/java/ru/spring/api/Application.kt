package ru.spring.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType


@ComponentScan(
        basePackages = ["ru.spring.api"],
        excludeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, value = [Configuration::class])]
)
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
