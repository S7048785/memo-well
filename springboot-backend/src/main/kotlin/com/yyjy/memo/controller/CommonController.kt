package com.yyjy.memo.controller

import com.yyjy.memo.common.R
import com.yyjy.memo.models.entity.Categories
import com.yyjy.memo.models.entity.by
import com.yyjy.memo.service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.client.meta.Api
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api
@Tag(name = "公共配置模块")
@RequestMapping("common")
@RestController
class CommonController(
	private val categoryService: CategoryService
) {

	@Api
	@Operation(summary = "获取所以分类")
	@GetMapping("/categories")
	fun categoryList(): R<List<@FetchBy("CATEGORY_ITEM") Categories>> = R.ok(categoryService.list(CATEGORY_ITEM))

	companion object {
		val CATEGORY_ITEM = newFetcher(Categories::class).by {
			name()
		}
	}
}