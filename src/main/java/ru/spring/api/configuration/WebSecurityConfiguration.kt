package ru.spring.api.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import ru.spring.api.service.ClientServiceImpl
import ru.spring.api.service.UserServiceImpl
import javax.servlet.Filter


@Configuration
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var clientDetailsService: ClientServiceImpl

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder())

        return authenticationProvider
    }

    @Bean
    fun preAuthenticatedAuthenticationProvider(): PreAuthenticatedAuthenticationProvider {
        val preAuthenticatedAuthenticationProvider = PreAuthenticatedAuthenticationProvider()
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(
                UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(
                        userDetailsService()
                )
        )

        return preAuthenticatedAuthenticationProvider
    }

    @Primary
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Autowired
    lateinit var userDetailsService: UserServiceImpl

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .authenticationProvider(preAuthenticatedAuthenticationProvider())
                .authenticationProvider(authenticationProvider())
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
    }

    @Bean
    fun myOAuth2Filter(): Filter {
        val filter = OAuth2AuthenticationProcessingFilter()
        filter.setAuthenticationManager(oauth2authenticationManager())
        //allow auth with cookies (not only with token)
        filter.setStateless(false)

        return filter
    }

    @Bean
    fun oauth2authenticationManager(): OAuth2AuthenticationManager {
        val authManager = OAuth2AuthenticationManager()
        authManager.setClientDetailsService(clientDetailsService)
        authManager.setTokenServices(tokenServices())

        return authManager
    }

    @Bean
    fun tokenServices() = AccessTokenServices()

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()

        http
                .addFilterBefore(
                        myOAuth2Filter(),
                        BasicAuthenticationFilter::class.java
                )
    }
}