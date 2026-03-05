package com.yyjy.memo.models

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
    val id: Int


    /**
     * 分类名称，如：表白、闲置、树洞
     */
    @Key
    val name: String

    /**
     * 分类代表色
     */
    val color: String?

    /**
     * 排序权重
     */
    val sortOrder: Int?
}

