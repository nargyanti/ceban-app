package com.example.ceban.core.datasource.local

import com.example.ceban.core.datasource.local.preferences.UserPreferences
import com.example.ceban.core.datasource.remote.responses.UserResponse

class UserLocalDataSource private constructor(private val userPreferences: UserPreferences){
    companion object {
        private var instance: UserLocalDataSource? = null
        fun getInstance(userPreferences: UserPreferences): UserLocalDataSource {
            return instance?: synchronized(this) {
                instance ?: UserLocalDataSource(userPreferences).apply {
                    instance = this
                }
            }
        }
    }

    fun saveUser(userResponse: UserResponse) {
        userPreferences.setUser(userResponse)
    }

    fun getUser(): UserResponse {
        return userPreferences.getUser()
    }
}