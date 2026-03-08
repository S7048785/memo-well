package com.yyjy.memo.service

import com.yyjy.memo.common.BusinessException
import com.yyjy.memo.models.entity.Users
import com.yyjy.memo.models.entity.dto.UserLoginRes
import com.yyjy.memo.models.entity.dto.UserRegisterReq
import com.yyjy.memo.models.entity.id
import com.yyjy.memo.repository.UserRepository
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

@Service
class UserService(
	private val userRepository: UserRepository
) {

	fun login(email: String, password: String): UserLoginRes {
		return userRepository.findUsersByEmail(email).firstOrNull()
			?.takeIf { it.password == password } // 如果密码匹配则返回对象，否则返回 null
			?.let { UserLoginRes(it) }
			?: throw BusinessException("邮箱或密码不正确") // 统一错误提示，增加安全性
	}

	fun register(userRegister: UserRegisterReq) {
		val users = userRepository.findUsersByEmailOrUsername(userRegister.email, userRegister.username)
		users.forEach { user ->
			when {
				user.email == userRegister.email -> throw BusinessException("邮箱已存在")
				user.username == userRegister.username -> throw BusinessException("用户名已存在")
			}
		}
	}

	fun getById(userId: Long, USER_LOGIN: Fetcher<Users>): Users = userRepository.sql.createQuery(Users::class) {
		where(table.id eq userId)
		select(table.fetch(USER_LOGIN))
	}.fetchFirstOrNull() ?: throw BusinessException("用户不存在")

}