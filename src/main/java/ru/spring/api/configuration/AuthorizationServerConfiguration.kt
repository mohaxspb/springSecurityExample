package ru.spring.api.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import ru.spring.api.Password
import ru.spring.api.service.UserServiceImpl


@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration() : AuthorizationServerConfigurerAdapter() {

//    @Autowired
//    private lateinit var clientDetailsService: ClientDetailsService

    @Autowired
    private lateinit var userDetailsService: UserServiceImpl

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
                .withClientDetails(userDetailsService)
                .withClient("client_id")
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("ADMIN", "USER")
                .scopes("read", "write")
                .secret(passwordEncoder.encode("client_secret"))
    }

    @Bean
    fun tokenStore(): TokenStore = InMemoryTokenStore()

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
    }

//    override fun configure(clients: ClientDetailsServiceConfigurer) {
//        clients.inMemory()
//                .withClient("client")
////                .secret("clientpassword")
//                .secret(Password.TEST)
//                .scopes("read", "write")
//                .authorizedGrantTypes("password")
//                .accessTokenValiditySeconds(3600)
//    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.allowFormAuthenticationForClients()
    }
}