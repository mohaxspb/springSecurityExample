package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.spring.api.bean.User

interface UsersRepository : JpaRepository<User, Long> {
    fun findOneByMyUsername(username: String): User
}