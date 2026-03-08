package com.yyjy.memo.models.entity

import org.babyfish.jimmer.sql.*
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
    val type: Int

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

    val likeCount: Long?

    /**
     * 浏览量
     */
    val viewCount: Long?

    /**
     * 是否置顶
     */
    @Column(name = "is_top")
    val pinned: Int?

    val createdAt: LocalDateTime?

    val updatedAt: LocalDateTime?
}

