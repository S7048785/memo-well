package com.yyjy.memo.satoken

import cn.dev33.satoken.stp.StpInterface
import com.yyjy.memo.models.entity.Users
import com.yyjy.memo.models.entity.id
import com.yyjy.memo.models.entity.role
import com.yyjy.memo.repository.UserRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Component

@Component
class StpInterfaceImpl(
	private val userRepository: UserRepository
) : StpInterface {
	override fun getPermissionList(p0: Any, p1: String): MutableList<String> {
		return mutableListOf()
	}

	/**
	 * 获取用户角色列表
	 */
	override fun getRoleList(p0: Any, p1: String): List<String?> {
		val roles = userRepository.sql.createQuery(Users::class) {
			where(table.id eq (p0 as String).toLong())
			select(table.role)
		}.execute()
		return roles
	}
}