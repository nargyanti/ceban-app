package com.example.ceban.ui.main

import androidx.lifecycle.ViewModel
import com.example.ceban.core.repository.ClassesRepository

class ClassesViewModel(private val classesRepository: ClassesRepository): ViewModel() {
    fun getAllClasses() = classesRepository.getAllClasses()
}