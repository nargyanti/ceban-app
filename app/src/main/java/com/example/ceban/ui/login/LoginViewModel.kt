package com.example.ceban.ui.login

import androidx.lifecycle.ViewModel
import com.example.ceban.core.datasource.local.entity.UserEntity
import com.example.ceban.core.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel(){
    fun login(username: String, password: String) = userRepository.login(username, password)

    fun saveUser(userEntity: UserEntity) = userRepository.saveUser(userEntity)

    fun getUser() = userRepository.getUser()
}