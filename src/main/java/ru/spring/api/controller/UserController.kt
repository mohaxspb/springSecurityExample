package ru.spring.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import ru.spring.api.bean.User
import ru.spring.api.service.UserService
import java.security.Principal


@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    fun showUsers() = userService.findAll()

    //todo use post with given model
    @Secured("ROLE_DBA")
    @RequestMapping("/update/{id}")
    fun updateUser(
            @PathVariable(value = "id") id: Long,
            @RequestParam(value = "first_name") firstName: String
    ): User {
        val user = userService.getById(id)
        user.nameFirst = firstName
        userService.update(user)

        return user
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") id: Long) = userService.getById(id)

    @GetMapping("/me")
    fun showMe(principal: Principal) = principal
}