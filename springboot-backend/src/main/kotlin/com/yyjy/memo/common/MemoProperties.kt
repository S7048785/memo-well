package com.yyjy.memo.common

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "memo") // 前缀锁定在 "memo"
data class MemoProperties(
    // 对应 memo.front 节点
    val front: FrontConfig = FrontConfig(),
    
    // 对应 memo.exclude 节点
    val exclude: ExcludeConfig = ExcludeConfig()
) {
    // 内部数据类：映射 memo.front
    data class FrontConfig(
        // 自动将 YAML 数组映射为 List<String>
        // 默认值为空列表，防止 null
        val ip: List<String> = emptyList()
    )

    // 内部数据类：映射 memo.exclude
    data class ExcludeConfig(
        // 自动将 YAML 数组映射为 List<String>
        val path: List<String> = emptyList()
    )
}