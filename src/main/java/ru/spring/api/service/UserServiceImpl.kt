package ru.spring.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.stereotype.Service
import ru.spring.api.bean.User
import ru.spring.api.repository.UsersRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import ru.spring.api.bean.UserNotFoundException
import java.util.*


@Service
class UserServiceImpl : UserService, ClientDetailsService {

    @Autowired
    private lateinit var repository: UsersRepository

    override fun findAll() = repository.findAll().toList()

    override fun getById(id: Long) = repository.getOne(id) ?: throw UserNotFoundException()


    override fun update(user: User): User = repository.save(user)

    override fun loadUserByUsername(username: String) = repository.findOneByMyUsername(username)

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    //todo create table for clients and search in it
    override fun loadClientByClientId(clientId: String): ClientDetails {
        println("clientId: $clientId")
        val user = repository.findOneByMyUsername("test@test.ru")

        println("user: $user")

        val details = BaseClientDetails()
        details.clientId = clientId
        details.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password"))
        details.setScope(Arrays.asList("read, trust", "admin"))
        details.clientSecret = passwordEncoder.encode("client_secret")
        val authorities = HashSet<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_CLIENT"))
        authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        details.setAuthorities(authorities)
        return details
    }
}