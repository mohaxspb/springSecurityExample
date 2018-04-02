package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.spring.api.bean.OAuthAccesToken
import ru.spring.api.bean.OAuthRefreshToken
import ru.spring.api.bean.User

interface RefreshTokenRepository : JpaRepository<OAuthRefreshToken, String>