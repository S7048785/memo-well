package com.yyjy.memo.controller

import cn.dev33.satoken.annotation.SaCheckRole
import cn.dev33.satoken.stp.StpUtil
import com.yyjy.memo.common.R
import com.yyjy.memo.models.entity.dto.UserLoginRes
import com.yyjy.memo.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.client.meta.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api
@Tag(name = "后台模块")
@RequestMapping("admin")
@RestController
class AdminController(
	private val userService: UserService
) {

	@Api
	@Operation(summary = "管理员登录")
	@PostMapping("/login")
	fun login(username: String, password: String): R<UserLoginRes> {
		val user = userService.login(username, password)
		StpUtil.login(user.id)
		return R.ok(UserLoginRes(user))
	}

	@Api
	@SaCheckRole("admin")
	@Operation(summary = "获取登录状态")
	@GetMapping("/me")
	fun getInfo(): R<UserLoginRes?> {
		val userId = StpUtil.getLoginIdAsLong()
		val user = userService.getById(userId, null)
		return R.ok(UserLoginRes(user))
	}

	@Api
	@SaCheckRole("admin")
	@Operation(summary = "退出登录")
	@GetMapping("/logout")
	fun logout(): R<String?> {
		StpUtil.logout()
		return R.ok()
	}


}