package com.yyjy.memo.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "便签模块")
@RequestMapping("posts")
@RestController
class PostController {
}