package com.yyjy.memo.config

import com.yyjy.common.ApiRes
import com.yyjy.common.BusinessException
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author Nyxcirea
 * @date 2026/3/6
 * @description: TODO
 */
@Hidden
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun handleBusinessException(ex: BusinessException): ApiRes<String?> {
        return ApiRes.fail( ex.message ?: "未知错误")
    }

//    @ExceptionHandler
//    fun handleException(ex: Exception): ApiRes<String?> {
//        return ApiRes.fail( HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误: ${ex.message}")
//    }
}