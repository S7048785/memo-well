package com.yyjy.memo.service

import com.yyjy.memo.models.entity.Posts
import com.yyjy.memo.models.entity.categoryId
import com.yyjy.memo.repository.PostRepository
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.springframework.stereotype.Service

@Service
class PostService(
	private val postRepository: PostRepository
) {
	fun notePage(limit: Int, size: Int, categoryId: Int?, postItem: Fetcher<Posts>) = postRepository.sql.createQuery(Posts::class) {
		where(table.categoryId `eq?` categoryId)
		select(table.fetch(postItem))
	}.fetchPage(limit - 1, size)
}