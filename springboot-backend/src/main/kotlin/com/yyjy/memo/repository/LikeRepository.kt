package com.yyjy.memo.repository

import com.yyjy.memo.models.entity.Likes
import com.yyjy.memo.models.entity.postId
import com.yyjy.memo.models.entity.userId
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq

interface LikeRepository : KRepository<Likes, Long> {
	fun findByUserAndPost(user: Long, post: Long) = sql.createQuery(Likes::class) {
		where(table.userId eq user)
		where(table.postId eq post)
		select(table)
	}.fetchFirstOrNull()
}