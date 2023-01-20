package com.example.composeinstagram.login.data.network

import com.example.composeinstagram.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(email: String, password: String): Boolean {
        val response = retrofit.create(LoginClient::class.java).doLogin()
        return response.body()?.success ?: false
    }
}
