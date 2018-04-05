package ru.spring.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.stereotype.Service
import ru.spring.api.bean.ClientNotFoundError
import ru.spring.api.repository.ClientDetailsRepository


@Service
class ClientServiceImpl : ClientDetailsService {

    @Autowired
    private lateinit var repository: ClientDetailsRepository

    override fun loadClientByClientId(clientId: String): ClientDetails {
        println("clientId: $clientId")
        val details = repository.getOne(clientId) ?: throw ClientNotFoundError()
        println("details: $details")
        return details
    }
}