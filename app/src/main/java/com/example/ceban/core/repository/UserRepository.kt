package com.example.ceban.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceban.core.datasource.local.UserLocalDataSource
import com.example.ceban.core.datasource.local.entity.UserEntity
import com.example.ceban.core.datasource.remote.UserRemoteDataSource
import com.example.ceban.core.datasource.remote.requests.LoginRequest
import com.example.ceban.utils.USER_DATA

class UserRepository private constructor(private val userLocalDataSource: UserLocalDataSource, private val userRemoteDataSource: UserRemoteDataSource){
    companion object {
        private var instance: UserRepository? = null
        fun getInstance(local: UserLocalDataSource, remoteDataSource: UserRemoteDataSource): UserRepository {
            return instance?: synchronized(this) {
                instance ?: UserRepository(local, remoteDataSource).apply {
                    instance = this
                }
            }
        }
    }

    fun login(username: String, password: String) = userRemoteDataSource.login(LoginRequest(username, password))

    fun getUser(): LiveData<UserEntity> {
        val liveData = MutableLiveData<UserEntity>()
        liveData.postValue(userLocalDataSource.getUser())
        return liveData
    }

    fun saveUser(userEntity: UserEntity) = userLocalDataSource.saveUser(userEntity)
}