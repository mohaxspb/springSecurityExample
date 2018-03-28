package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.spring.api.bean.Authority
import ru.spring.api.bean.User

@Repository
interface AuthoritiesRepository : JpaRepository<Authority, Long>