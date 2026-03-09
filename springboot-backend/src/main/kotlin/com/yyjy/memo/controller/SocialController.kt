package com.yyjy.memo.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.stp.StpUtil
import com.yyjy.memo.common.R
import com.yyjy.memo.models.entity.dto.CommentsCreateInput
import com.yyjy.memo.service.CategoryService
import com.yyjy.memo.service.CommentService
import com.yyjy.memo.service.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.client.meta.Api
import org.springframework.web.bind.annotation.*

@Api
@Tag(name = "评论模块")
@RequestMapping("social")
@RestController
class SocialController(
	private val commentService: CommentService,
	private val categoryService: CategoryService,
	private val postService: PostService
) {

	@Api
	@SaCheckLogin
	@Operation(summary = "点赞/取消")
	@PostMapping("/like")
	fun like(postId: Long): R<Boolean> {
		val isLiked = postService.like(postId)
		return R.ok(isLiked)
	}

	@Api
	@SaCheckLogin
	@Operation(summary = "发表评论")
	@PostMapping("/comment")
	fun postComment(@RequestBody comment: CommentsCreateInput): R<String?> {
		commentService.create(comment)
		return R.ok()
	}

	@Api
	@Operation(summary = "获取评论")
	@GetMapping("/comment/{post_id}")
	fun getCommentList(@PathVariable("post_id") postId: Long, page: Int, limit: Int) {
		TODO()
	}
}