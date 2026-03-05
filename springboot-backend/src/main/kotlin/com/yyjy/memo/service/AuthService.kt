package com.yyjy.memo.service

import com.yyjy.memo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
	private val userRepository: UserRepository
) {
}