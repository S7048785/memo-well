package com.yyjy.memo.repository

import com.yyjy.memo.models.Users
import org.babyfish.jimmer.spring.repository.KRepository

interface UserRepository : KRepository<Users, Long>