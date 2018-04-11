package ru.spring.api.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class MyFilter : GenericFilterBean() {

    @Autowired
    lateinit var tokenStore: TokenStore

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        if (SecurityContextHolder.getContext().authentication == null) {
            val token = request.getParameter("access_token")

            val auth = tokenStore.readAuthentication(token)
            println("auth: $auth")

            SecurityContextHolder.getContext().authentication = auth;
        } else {
            println("SecurityContextHolder not populated with auth, as it already contained: '"
                    + SecurityContextHolder.getContext().getAuthentication() + "'")
        }
        chain.doFilter(request, response)
    }
}