package com.yyjy.memo.repository

import com.yyjy.memo.models.entity.Users
import org.babyfish.jimmer.spring.repository.KRepository

interface UserRepository : KRepository<Users, Long> {
	fun findUsersByEmail(email: String): MutableList<Users>
	fun findUsersByEmailOrUsername(email: String, username: String): MutableList<Users>
}