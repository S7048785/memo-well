package com.yyjy.memo.common

/**
 * @author Nyxcirea
 * @date 2026/3/6
 * @description: TODO
 */
class BusinessException(message: String, code: Int = 500) : RuntimeException(message)