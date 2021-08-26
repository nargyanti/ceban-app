package com.example.ceban.core.repository

import com.example.ceban.core.datasource.local.UserLocalDataSource
import com.example.ceban.core.datasource.remote.responses.UserResponse
import com.example.ceban.utils.USER_DATA

class UserRepository private constructor(private val userLocalDataSource: UserLocalDataSource){
    companion object {
        private var instance: UserRepository? = null
        fun getInstance(userLocalDataSource: UserLocalDataSource): UserRepository {
            return instance?: synchronized(this) {
                instance ?: UserRepository(userLocalDataSource).apply {
                    instance = this
                }
            }
        }
    }

    fun login(username: String, password: String) {
        val data = USER_DATA
        for (user: UserResponse in data) {
            if (user.username == username && user.password == password) {
                userLocalDataSource.saveUser(user)
            }
        }
    }

    fun getUser(): UserResponse {
        return userLocalDataSource.getUser()
    }
}