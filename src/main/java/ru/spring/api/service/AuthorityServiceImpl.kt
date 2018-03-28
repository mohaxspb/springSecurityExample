package ru.spring.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.spring.api.bean.Authority
import ru.spring.api.bean.User
import ru.spring.api.repository.AuthoritiesRepository


@Service
class AuthorityServiceImpl : AuthorityService {

    @Autowired
    private lateinit var repository: AuthoritiesRepository

    override fun findAll(): List<Authority> {
        val data = repository.findAll()

        return data.toList()
    }
}