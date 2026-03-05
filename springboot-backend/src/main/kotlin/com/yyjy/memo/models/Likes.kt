package com.yyjy.memo.models

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.GenerationType
import java.time.LocalDateTime

/**
* Entity for table "likes"
*/
    @Entity
interface Likes {

    @Id
    @GeneratedValue(            strategy = GenerationType.IDENTITY
)
    val id: long

    @Key
    val userId: Long

    @Key
    val postId: Long

    val createdAt: LocalDateTime?
}

