package ru.spring.api.service

import org.springframework.security.core.userdetails.UserDetailsService
import ru.spring.api.bean.User

interface UserService: UserDetailsService {
    fun findAll(): List<User>
    fun getById(id: Long): User
    fun update(user: User): User
}