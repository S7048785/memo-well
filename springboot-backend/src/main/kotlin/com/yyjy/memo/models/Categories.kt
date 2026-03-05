package com.yyjy.memo.models

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.GenerationType

/**
* Entity for table "categories"
*/
    @Entity
interface Categories {

    @Id
    @GeneratedValue(            strategy = GenerationType.IDENTITY
)
    val id: long

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
    val sortOrder: Integer?
}

