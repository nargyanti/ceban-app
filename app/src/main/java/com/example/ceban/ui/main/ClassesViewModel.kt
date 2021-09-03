package com.example.ceban.ui.main

import androidx.lifecycle.ViewModel
import com.example.ceban.core.repository.ClassesRepository
import com.example.ceban.core.repository.UserRepository

class ClassesViewModel(private val classesRepository: ClassesRepository, private val userRepository: UserRepository): ViewModel() {
    fun getAllClasses(id: Int) = classesRepository.getAllSubject(id)

    fun getUser() = userRepository.getUser()
}