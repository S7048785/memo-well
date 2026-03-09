package com.yyjy.memo.models.entity

import org.babyfish.jimmer.sql.*

/**
 * Entity for table "categories"
 */
@Entity
interface Categories {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Long


    /**
     * 分类名称，如：表白、闲置、树洞
     */
    @Key
    val name: String

    /**
     * 排序权重
     */
    val sortOrder: Int?
}

