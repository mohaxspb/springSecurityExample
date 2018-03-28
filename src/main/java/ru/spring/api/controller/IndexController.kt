package ru.spring.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.spring.api.service.AuthorityService
import ru.spring.api.service.UserService


@RestController
class IndexController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var authoritiesService: AuthorityService

    @GetMapping
    fun index(): String = "Greetings from Spring Boot!"

    @GetMapping("/hello")
    fun test(@RequestParam(value = "name", defaultValue = "World") name: String) = "Hello, $name"

    @GetMapping("/showUsers")
    fun showUsers() = userService.findAll()

    @GetMapping("/showAuthorities")
    fun showAuthorities() = authoritiesService.findAll()
}