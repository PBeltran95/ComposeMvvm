package com.example.composeinstagram.login.data.network

import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient) {

    suspend fun doLogin(email: String, password: String): Boolean {
        val response = loginClient.doLogin()
        return response.body()?.success ?: false
    }
}
