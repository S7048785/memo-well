package com.yyjy.memo.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "权限模块")
@RequestMapping("auth")
@RestController
class AuthController {
}