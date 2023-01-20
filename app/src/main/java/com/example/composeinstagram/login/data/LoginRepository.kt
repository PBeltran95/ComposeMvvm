package com.example.composeinstagram.login.data

import com.example.composeinstagram.login.data.network.LoginService

class LoginRepository {

    private val api = LoginService()

    suspend fun doLogin(email: String, password: String): Boolean {
        return api.doLogin(email, password)
    }
}
