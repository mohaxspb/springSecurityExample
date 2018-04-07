package ru.spring.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.spring.api.bean.OAuthClientDetails

interface ClientDetailsRepository : JpaRepository<OAuthClientDetails, String>