package com.yyjy.memo.controller

import com.yyjy.memo.common.PageRes
import com.yyjy.memo.models.entity.Posts
import com.yyjy.memo.models.entity.by
import com.yyjy.memo.service.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.client.meta.Api
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.web.bind.annotation.*

@Api
@Tag(name = "便签模块")
@RequestMapping("posts")
@RestController
class PostController(
	private val postService: PostService
) {

	@Api
	@Operation(description = "分页获取便签列表")
	@GetMapping("/")
	fun getNoteList(limit: Int, size: Int, categoryId: Int?): PageRes<@FetchBy("NOTE_ITEM") Posts> {
		val page = postService.notePage(limit, size, categoryId, NOTE_ITEM)
		return PageRes(page.totalRowCount, limit, size, page.rows)
	}

	@Api
	@Operation(description = "获取详情")
	@GetMapping("/{id}")
	fun getDetail(@PathVariable("id") id: Long) {
		TODO()
	}

	@Api
	@Operation(description = "发布便签")
	@PostMapping("/")
	fun postNote() {
		TODO()
	}

	@Api
	@Operation(description = "发布照片")
	@PostMapping("/upload")
	fun postPhoto() {
		TODO()
	}

	@Api
	@Operation(description = "删除内容")
	@DeleteMapping("/{id}")
	fun delete(@PathVariable("id") postId: Long) {
		TODO()
	}

	@Api
	@Operation(description = "根据用户名搜索内容")
	@GetMapping("/search")
	fun search(username: String) {
		TODO()
	}

	companion object {
		val NOTE_ITEM  = newFetcher(Posts::class).by {
			category {
				name()
			}
			content()
			cardColor()
			user {
				username()
			}
			likeCount()
			viewCount()
			createdAt()
		}
	}
}