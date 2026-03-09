package com.yyjy.memo.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import com.yyjy.memo.common.PageRes
import com.yyjy.memo.common.R
import com.yyjy.memo.models.entity.Posts
import com.yyjy.memo.models.entity.by
import com.yyjy.memo.models.entity.dto.NoteCreateInput
import com.yyjy.memo.models.entity.dto.PhotoCreateInput
import com.yyjy.memo.service.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.Page
import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.client.meta.Api
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Api
@Tag(name = "帖子模块")
@RequestMapping("posts")
@RestController
class PostController(
	private val postService: PostService
) {

	@Api
	@Operation(summary = "分页获取帖子列表")
	@GetMapping("/")
	fun getNoteList(limit: Int, size: Int, categoryId: Long?): PageRes<@FetchBy("POST_ITEM") Posts> {
		val page = postService.notePage(limit, size, categoryId, POST_ITEM)
		return PageRes(page.totalRowCount, limit, size, page.rows)
	}

	@Api
	@Operation(summary = "获取详情")
	@GetMapping("/{id}")
	fun getDetail(@PathVariable("id") id: Long) {
//		postService.getDetail(id)
		TODO()
	}

	@Api
	@SaCheckLogin
	@Operation(summary = "发布便签")
	@PostMapping("/")
	fun postNote(@Validated @RequestBody note: NoteCreateInput): R<String?> {
		postService.postNote(note)
		return R.ok()
	}

	@Api
	@SaCheckLogin
	@Operation(summary = "发布照片")
	@PostMapping("/upload")
	fun postPhoto(@Validated @RequestBody photo: PhotoCreateInput): R<String?> {
		postService.postPhoto(photo)
		return R.ok()
	}

	@Api
	@SaCheckLogin
	@Operation(summary = "删除内容")
	@DeleteMapping("/{id}")
	fun delete(@PathVariable("id") postId: Long): R<String?> {
		postService.delete(postId)
		return R.ok()
	}

	@Api
	@Operation(summary = "根据用户名搜索便签")
	@GetMapping("/search")
	fun search(username: String, limit: Int, size: Int): PageRes<@FetchBy("POST_ITEM") Posts> {
		val page: Page<Posts> = postService.pageByUsername(username, limit, size, POST_ITEM)
		return PageRes(page.totalRowCount, limit, size, page.rows)
	}

	companion object {
		val POST_ITEM  = newFetcher(Posts::class).by {
			category {
				name()
			}
			content()
			imageUrl()
			cardColor()
			user {
				username()
			}
			likeCount()
			commentCount()
			createdAt()
		}
	}
}