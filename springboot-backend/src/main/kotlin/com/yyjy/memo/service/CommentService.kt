package com.yyjy.memo.service

import cn.dev33.satoken.stp.StpUtil
import com.yyjy.memo.models.entity.dto.CommentsCreateInput
import com.yyjy.memo.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
	private val commentRepository: CommentRepository
) {
	fun create(comment: CommentsCreateInput) {
		val currentUserId = StpUtil.getLoginIdAsLong()
		commentRepository.save(comment.toEntity {
			userId = currentUserId
		})
	}
}