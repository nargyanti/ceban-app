package com.example.ceban.core.datasource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceban.core.datasource.remote.api.UserService
import com.example.ceban.core.datasource.remote.requests.LoginRequest
import com.example.ceban.core.datasource.remote.responses.ApiResponse
import com.example.ceban.core.datasource.remote.responses.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSource private constructor( private val userService: UserService){
    companion object {
        private var instance: UserRemoteDataSource? = null

        fun getInstance(userService: UserService) = instance?: synchronized(this) {
            instance ?: UserRemoteDataSource(userService).apply {
                instance = this
            }
        }
    }

    fun login(loginRequest: LoginRequest): LiveData<ApiResponse<UserResponse>> {
        val liveData = MutableLiveData<ApiResponse<UserResponse>>()
        userService.login(loginRequest).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        liveData.value = ApiResponse.success(data)
                    }
                }else{
                    liveData.value = ApiResponse.error(response.message(), UserResponse())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                liveData.value = ApiResponse.error(t.message, UserResponse())
            }

        })
        return liveData
    }
}