package ru.spring.api.bean

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*

@Entity
@IdClass(KeyUserAuthority::class)
@Table(name = "authorities")
data class Authority(
        @Id
        @Column(name = "user_id")
        var userId: Long,
        @Id
        @Column(name = "authority")
        var authority: String,
        @CreationTimestamp
        @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val created: Timestamp,
        @UpdateTimestamp
        @Version
        @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val updated: Timestamp
) : Serializable