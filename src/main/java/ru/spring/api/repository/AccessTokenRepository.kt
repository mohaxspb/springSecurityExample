package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.spring.api.bean.OAuthAccesToken
import ru.spring.api.bean.User

interface AccessTokenRepository : JpaRepository<OAuthAccesToken, String>