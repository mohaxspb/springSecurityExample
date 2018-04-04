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
@Table(name = "oauth_access_token")
data class OAuthAccessToken(
        @Id
        val token_id: String,
        @Lob
        val token:ByteArray,
        val authentication_id:String,
        val user_name:String,
        val client_id:String,
        @Lob
        val authentication:ByteArray,
        val refresh_token:String,
        @CreationTimestamp
        @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val created: Timestamp,
        @UpdateTimestamp
        @Version
        @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val updated: Timestamp
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as OAuthAccessToken

                if (token_id != other.token_id) return false

                return true
        }

        override fun hashCode(): Int {
                return token_id.hashCode()
        }
}
//create table oauth_access_token (
//token_id VARCHAR(256),
//token bytea,
//authentication_id VARCHAR(256),
//user_name VARCHAR(256),
//client_id VARCHAR(256),
//authentication bytea,
//refresh_token VARCHAR(256)
//);