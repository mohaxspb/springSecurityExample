package ru.spring.api.bean

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.ResponseStatus
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(name = "name_first")
        var nameFirst: String,
        @Column(name = "name_second")
        var nameSecond: String,
        @Column(name = "name_third")
        var nameThird: String,
        var role: String,
        var email: String,
        @Column(name = "password")
        var myPassword: String,
        var avatar: String,
        @CreationTimestamp
        @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val created: Timestamp,
        @UpdateTimestamp
        @Version
        @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val updated: Timestamp
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(SimpleGrantedAuthority("ADMIN"))

    override fun isEnabled() = true

    override fun getUsername() = email

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = myPassword

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
class UserNotFoundException : RuntimeException()