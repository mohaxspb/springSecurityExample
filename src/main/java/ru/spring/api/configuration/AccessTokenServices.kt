package ru.spring.api.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore

class AccessTokenServices : ResourceServerTokenServices {

    @Autowired
    lateinit var tokenStore: TokenStore

    override fun loadAuthentication(accessToken: String) = tokenStore.readAuthentication(accessToken)

    override fun readAccessToken(accessToken: String) = tokenStore.readAccessToken(accessToken)
}