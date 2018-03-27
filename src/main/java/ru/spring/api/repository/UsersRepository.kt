package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.spring.api.bean.User

@Repository
interface UsersRepository : JpaRepository<User, Long> {
    fun findOneByEmail(username: String): User
}