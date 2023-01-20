package com.example.composeinstagram.login.domain

import com.example.composeinstagram.login.data.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(email: String, password: String): Boolean {
        return repository.doLogin(email, password)
    }
}
