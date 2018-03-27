package ru.spring.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.spring.api.bean.User
import ru.spring.api.repository.UsersRepository


@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var repository: UsersRepository

    override fun findAll(): List<User> {
        val data = repository.findAll()

        return data.toList()
    }

    override fun getById(id: Long): User? {
        val user = repository.getOne(id)

        return user
    }

    override fun update(user: User): User = repository.save(user)

    override fun loadUserByUsername(username: String) = repository.findOneByEmail(username)
}