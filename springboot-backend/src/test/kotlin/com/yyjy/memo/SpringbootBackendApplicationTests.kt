package com.yyjy.memo

import com.yyjy.memo.models.entity.Users
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringbootBackendApplicationTests(
    private val kSqlClient: KSqlClient
) {

    @Test
    fun contextLoads() {
        kSqlClient.save(Users {
            email = "asdasd"
            username = "usernam1"
            password = "password1"
        }, SaveMode.INSERT_ONLY)

        println()
    }

}
