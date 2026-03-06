package com.yyjy.memo.repository

import com.yyjy.memo.models.entity.Categories
import org.babyfish.jimmer.spring.repository.KRepository

interface CategoriesRepository : KRepository<Categories, Long> {
}