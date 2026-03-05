package com.yyjy.memo.common

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author Nyxcirea
 * @date 2026/3/6
 * @description: TODO
 */

@ConfigurationProperties("com.yyjy.memo")
data class ExcludePathProperties(
    val paths: List<String>
)