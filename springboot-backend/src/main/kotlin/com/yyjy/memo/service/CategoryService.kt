package com.yyjy.memo.service

import com.yyjy.memo.models.entity.Categories
import com.yyjy.memo.repository.CategoriesRepository
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.springframework.stereotype.Service

@Service
class CategoryService(
	private val categoriesRepository: CategoriesRepository
) {
	fun list(fetcher: Fetcher<Categories>): List<Categories> {
		return categoriesRepository.findAll(fetcher)
	}
}