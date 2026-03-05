package com.yyjy.memo

import com.yyjy.memo.common.ExcludePathProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(ExcludePathProperties::class)
@SpringBootApplication
class SpringbootBackendApplication

fun main(args: Array<String>) {
    runApplication<SpringbootBackendApplication>(*args)
}
