package com.example.ceban.core.datasource.local

import com.example.ceban.core.datasource.local.preferences.UserPreferences
import com.example.ceban.core.datasource.local.entity.UserEntity

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

    fun saveUser(userEntity: UserEntity) {
        userPreferences.setUser(userEntity)
    }

    fun getUser(): UserEntity {
        return userPreferences.getUser()
    }
}