package com.yyjy.memo.service

import com.yyjy.memo.repository.CategoriesRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
	private val categoriesRepository: CategoriesRepository
) {
}