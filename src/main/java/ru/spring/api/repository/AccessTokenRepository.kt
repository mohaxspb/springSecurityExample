package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.spring.api.bean.OAuthAccessToken

interface AccessTokenRepository : JpaRepository<OAuthAccessToken, String>