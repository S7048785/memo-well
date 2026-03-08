package com.yyjy.memo.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.client.meta.Api
import org.springframework.web.bind.annotation.*

@Api
@Tag(name = "评论模块")
@RequestMapping("social")
@RestController
class SocialController {

	@Api
	@Operation(description = "点赞/取消")
	@PostMapping("/like")
	fun like() {
		TODO()
	}

	@Api
	@Operation(description = "发表评论")
	@PostMapping("/comment")
	fun postComment() {
		TODO()
	}

	@Api
	@Operation(description = "获取评论")
	@GetMapping("/comment/{post_id}")
	fun getCommentList(@PathVariable("post_id") postId: Long, page: Int, limit: Int) {
		TODO()
	}
}