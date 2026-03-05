package com.yyjy.memo.service

import com.yyjy.memo.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
	private val postRepository: PostRepository
) {
}