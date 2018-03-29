package ru.spring.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.spring.api.service.UserService
import ru.spring.api.bean.User
import ru.spring.api.bean.UserNotFoundException
import java.security.Principal


@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/all")
    fun showUsers() = userService.findAll()

    //todo use post with given model
    @RequestMapping("/update/{id}")
    fun updateUser(
            @PathVariable(value = "id") id: Long,
            @RequestParam(value = "first_name") firstName: String
    ): User {
        val user = userService.getById(id) ?: throw UserNotFoundException()
        user.nameFirst = firstName
        userService.update(user)

        return user
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") id: Long) = userService.getById(id)

    @GetMapping("/me")
    fun showMe(principal: Principal): Principal {
        println("principal: $principal")
        return principal
    }
}