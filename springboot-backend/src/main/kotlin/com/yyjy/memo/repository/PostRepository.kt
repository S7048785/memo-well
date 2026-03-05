package com.yyjy.memo.repository

import com.yyjy.memo.models.Posts
import org.babyfish.jimmer.spring.repository.KRepository

interface PostRepository : KRepository<Posts, Long> {
}