package com.yyjy.memo.service

import com.yyjy.memo.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
	private val commentRepository: CommentRepository
) {
}