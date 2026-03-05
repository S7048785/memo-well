package com.yyjy.memo.models

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.GenerationType
import java.time.LocalDateTime

/**
* Entity for table "comments"
*/
    @Entity
interface Comments {

    @Id
    @GeneratedValue(            strategy = GenerationType.IDENTITY
)
    val id: long

        /**
        * 所属帖子ID
        */
    @Key
    val postId: Long

        /**
        * 评论者ID
        */
    val userId: Long

        /**
        * 父评论ID（用于回复功能）
        */
    val parentId: Long?

        /**
        * 评论内容
        */
    val content: String

    val createdAt: LocalDateTime?
}

