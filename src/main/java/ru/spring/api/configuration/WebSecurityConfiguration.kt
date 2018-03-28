package ru.spring.api.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import ru.spring.api.Password


@Configuration
@EnableWebSecurity
@EnableResourceServer
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

//    @Autowired
//    lateinit var userDetailsService: UserDetailsService

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

//    @Autowired
//    fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder())
//    }

//    override fun configure(http: HttpSecurity) {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/users/**").authenticated()
//                    .antMatchers("/**", "/oauth/**").permitAll()
//            .and()
//                .formLogin()
//    }

    @Bean(name = arrayOf(BeanIds.AUTHENTICATION_MANAGER))
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    /////////
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user")
//                .password("user")
//                .password(Password.USER_ENCYPTED)
                .password(passwordEncoder().encode("user"))
                .roles("ROLE")
    }

    public override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
    }

//    override fun configure(web: WebSecurity) {
//        web.ignoring().antMatchers("/**")
//    }
}