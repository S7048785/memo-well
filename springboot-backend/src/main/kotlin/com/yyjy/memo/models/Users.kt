package com.yyjy.memo.models

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.GenerationType
import java.time.LocalDateTime

/**
* Entity for table "users"
*/
    @Entity
interface Users {

    @Id
    @GeneratedValue(            strategy = GenerationType.IDENTITY
)
    val id: long

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

    val createdAt: LocalDateTime?

    val updatedAt: LocalDateTime?
}

