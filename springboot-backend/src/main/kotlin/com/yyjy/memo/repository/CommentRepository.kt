package com.yyjy.memo.repository

import com.yyjy.memo.models.Comments
import org.babyfish.jimmer.spring.repository.KRepository

interface CommentRepository : KRepository<Comments, Long> {
}