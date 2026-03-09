package com.yyjy.memo.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.stp.StpUtil
import com.yyjy.memo.common.R
import com.yyjy.memo.models.entity.Users
import com.yyjy.memo.models.entity.by
import com.yyjy.memo.models.entity.dto.UserLoginReq
import com.yyjy.memo.models.entity.dto.UserLoginRes
import com.yyjy.memo.models.entity.dto.UserRegisterReq
import com.yyjy.memo.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.client.meta.Api
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Api
@Tag(name = "前台用户模块")
@RequestMapping("user")
@RestController
class UserController(
	private val userService: UserService
) {

	@Api
	@Operation(summary = "用户登录")
	@PostMapping("/login")
	fun login(@Validated @RequestBody userLogin: UserLoginReq): R<UserLoginRes> {
		val user = userService.login(userLogin.email, userLogin.password)
		StpUtil.login(user.id)
		return R.ok(UserLoginRes(user))
	}

	@Api
	@Operation(summary = "用户注册")
	@PostMapping("/register")
	fun register(@Validated @RequestBody userRegister: UserRegisterReq): R<String?> {
		userService.register(userRegister)
		return R.ok()
	}

	@Api
	@SaCheckLogin
	@Operation(summary = "获取当前用户信息")
	@GetMapping("/me")
	fun get(): R<@FetchBy("USER_LOGIN") Users> {
		val userId = StpUtil.getLoginIdAsLong()
		val res = userService.getById(userId, USER_LOGIN)
		return R.ok(res)
	}

	companion object {
		val USER_LOGIN = newFetcher(Users::class).by {
			email()
			username()
			avatar()
		}
	}
}