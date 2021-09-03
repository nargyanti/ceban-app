package com.example.ceban.core.datasource.remote.api

import com.example.ceban.core.datasource.remote.requests.LoginRequest
import com.example.ceban.core.datasource.remote.responses.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<UserResponse>
}