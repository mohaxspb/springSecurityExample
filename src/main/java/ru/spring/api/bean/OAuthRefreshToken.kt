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
@Table(name = "oauth_refresh_token")
data class OAuthRefreshToken(
        @Id
        val token_id: String,
        @Lob
        val token:String,
        @Lob
        val authentication_id:String,
        @CreationTimestamp
        @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val created: Timestamp,
        @UpdateTimestamp
        @Version
        @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val updated: Timestamp
)
//create table oauth_refresh_token (
//token_id VARCHAR(256),
//token bytea,
//authentication bytea
//);