package com.yyjy.memo.models

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 * Entity for table "comments"
 */
@Entity
interface Comments {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Long


    /**
     * 所属帖子ID
     */
    @Key
    @ManyToOne
    val post: Posts

    /**
     * 评论者ID
     */
    @ManyToOne
    val user: Users

    /**
     * 父评论ID（用于回复功能）
     */
    @ManyToOne
    val parent: Comments?

    @OneToMany(mappedBy = "parent")
    val children: List<Comments>

    /**
     * 评论内容
     */
    val content: String

    val createdAt: LocalDateTime?
}

