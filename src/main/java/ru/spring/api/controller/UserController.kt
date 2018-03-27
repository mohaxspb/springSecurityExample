package ru.spring.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.spring.api.service.UserService
import ru.spring.api.bean.User
import ru.spring.api.bean.UserNotFoundException


@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @RequestMapping("/all")
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

    @RequestMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") id: Long) = userService.getById(id)
}