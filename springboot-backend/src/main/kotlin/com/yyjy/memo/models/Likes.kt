package com.yyjy.memo.models

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.GenerationType
import org.babyfish.jimmer.sql.ManyToOne
import java.time.LocalDateTime

/**
 * Entity for table "likes"
 */
@Entity
interface Likes {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Long


    @Key
    @ManyToOne
    val user: Users

    @Key
    @ManyToOne
    val post: Posts

    val createdAt: LocalDateTime?
}

