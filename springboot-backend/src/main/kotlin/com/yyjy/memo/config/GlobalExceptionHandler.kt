package com.yyjy.memo.config

import com.yyjy.memo.common.BusinessException
import com.yyjy.memo.common.R
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    fun handleBusinessException(ex: BusinessException): R<String?> {
        return R.fail( ex.message ?: "未知错误")
    }

    /**
     * 捕获参数验证失败的异常
     */
    @ExceptionHandler
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): R<String> {
        val errorMsgList = exception.bindingResult.fieldErrors.mapNotNull { it.defaultMessage }
        val errorMessage = errorMsgList.joinToString(separator = ";")

        return R.fail(errorMessage)
    }

//    @ExceptionHandler
//    fun handleException(ex: Exception): ApiRes<String?> {
//        return ApiRes.fail( HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误: ${ex.message}")
//    }
}