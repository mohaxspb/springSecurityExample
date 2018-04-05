package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.spring.api.bean.OAuthClientToken

interface ClientTokenRepository : JpaRepository<OAuthClientToken, String>