package com.yyjy.memo.service

import cn.dev33.satoken.stp.StpUtil
import com.yyjy.memo.common.BusinessException
import com.yyjy.memo.models.entity.Likes
import com.yyjy.memo.models.entity.Posts
import com.yyjy.memo.models.entity.categoryId
import com.yyjy.memo.models.entity.dto.NoteCreateInput
import com.yyjy.memo.models.entity.dto.PhotoCreateInput
import com.yyjy.memo.models.entity.type
import com.yyjy.memo.repository.LikeRepository
import com.yyjy.memo.repository.PostRepository
import org.babyfish.jimmer.Page
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class PostService(
	private val postRepository: PostRepository,
	private val likeRepository: LikeRepository
) {
	fun notePage(limit: Int, size: Int, categoryId: Long?, postItem: Fetcher<Posts>) =
		postRepository.sql.createQuery(Posts::class) {
			where(table.categoryId `eq?` categoryId)
			select(table.fetch(postItem))
		}.fetchPage(limit - 1, size)

	fun postNote(note: NoteCreateInput) {
		val currentUserId = StpUtil.getLoginIdAsLong()
		postRepository.save(note.toEntity {
			userId = currentUserId
			type = 1
		}, SaveMode.INSERT_ONLY)
	}

	fun postPhoto(photo: PhotoCreateInput) {
		val currentUserId = StpUtil.getLoginIdAsLong()
		postRepository.save(photo.toEntity {
			userId = currentUserId
			type = 2
		}, SaveMode.INSERT_ONLY)
	}

	fun delete(postId: Long) {
		val currentUserId = StpUtil.getLoginIdAsLong()
		val post = postRepository.findById(postId).getOrNull() ?: throw BusinessException("帖子不存在")
		if (post.user.id != currentUserId)
			throw BusinessException("删除失败: 不是你的帖子")
		postRepository.deleteById(postId)
		TODO("添加校验管理员")
	}

	fun pageByUsername(username: String, limit: Int, size: Int, fetcher: Fetcher<Posts>): Page<Posts> =
		postRepository.sql.createQuery(Posts::class) {
			where(table.type eq 1)
			select(table.fetch(fetcher))
		}.fetchPage(limit - 1, size)

	fun like(currentPostId: Long): Boolean {
		val currentUserId = StpUtil.getLoginIdAsLong()
		val likeRecord = likeRepository.findByUserAndPost(currentUserId, currentPostId)
		if (likeRecord == null) {
			likeRepository.save(Likes {
				userId = currentUserId
				postId = currentPostId
			}, SaveMode.INSERT_ONLY)
			return true
		}
		return false
	}
}