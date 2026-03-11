package com.yyjy.memo.service

import com.yyjy.memo.common.BusinessException
import com.yyjy.memo.models.entity.Users
import com.yyjy.memo.models.entity.dto.UserRegisterReq
import com.yyjy.memo.models.entity.id
import com.yyjy.memo.repository.UserRepository
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

@Service
class UserService(
	private val userRepository: UserRepository
) {

	fun login(email: String, password: String): Users {
		return userRepository.findUsersByEmail(email).firstOrNull()
			?.takeIf { it.password == password }
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
		userRepository.save(userRegister,SaveMode.INSERT_ONLY)
	}

	fun getById(userId: Long, USER_LOGIN: Fetcher<Users>?): Users = userRepository.sql.createQuery(Users::class) {
		where(table.id eq userId)
		select(if (USER_LOGIN != null) table.fetch(USER_LOGIN) else table)
	}.fetchFirstOrNull() ?: throw BusinessException("用户不存在")



}