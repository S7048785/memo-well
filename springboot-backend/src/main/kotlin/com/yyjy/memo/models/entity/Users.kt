package com.yyjy.memo.models.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 * Entity for table "users"
 */
@Entity
interface Users {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Long

    val email: String

    /**
     * 用户名
     */
    @Key
    val username: String

    /**
     * 加密后的密码
     */
    val password: String

    /**
     * 头像URL
     */
    val avatar: String?

    val bio: String?

    val gender: Int?

    val createdAt: LocalDateTime?

    val updatedAt: LocalDateTime?
}

