package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.spring.api.bean.OAuthRefreshToken

interface RefreshTokenRepository : JpaRepository<OAuthRefreshToken, String>