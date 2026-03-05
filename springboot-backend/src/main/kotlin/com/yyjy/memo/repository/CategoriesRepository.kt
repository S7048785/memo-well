package com.yyjy.memo.repository

import com.yyjy.memo.models.Categories
import org.babyfish.jimmer.spring.repository.KRepository

interface CategoriesRepository : KRepository<Categories, Long> {
}