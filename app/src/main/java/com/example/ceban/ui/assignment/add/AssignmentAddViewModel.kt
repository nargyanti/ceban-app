package com.example.ceban.ui.assignment.add

import androidx.lifecycle.ViewModel
import com.example.ceban.core.datasource.remote.requests.AssignmentAddRequest
import com.example.ceban.core.repository.ClassesRepository
import com.example.ceban.core.repository.UserRepository

class AssignmentAddViewModel(
    private val classesRepository: ClassesRepository) : ViewModel()
{
    fun addAssignment(request: AssignmentAddRequest) = classesRepository.addAssignment(request)
}