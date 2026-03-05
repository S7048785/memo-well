package com.yyjy.memo.models

import org.babyfish.jimmer.sql.Column
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.GenerationType
import org.babyfish.jimmer.sql.JoinColumn
import org.babyfish.jimmer.sql.ManyToOne
import java.time.LocalDateTime

/**
 * Entity for table "posts"
 */
@Entity
interface Posts {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Long


    /**
     * 发布者ID
     */
    @Key
    @ManyToOne
    val user: Users

    /**
     * 分类ID
     */
    @Key
    @JoinColumn(name = "")
    @ManyToOne
    val category: Categories

    /**
     * 1:便签(文字), 2:照片
     */
    @Key
    val type: Int?

    /**
     * 文字内容
     */
    val content: String?

    /**
     * 图片存储路径
     */
    val imageUrl: String?

    /**
     * 卡片背景颜色
     */
    val cardColor: String?

    /**
     * 浏览量
     */
    val viewCount: Long?

    /**
     * 是否置顶
     */
    @Column(name = "isTop")
    val pinned: Int?

    val createdAt: LocalDateTime?

    val updatedAt: LocalDateTime?
}

