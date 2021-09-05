package com.example.ceban.ui.assignment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ceban.core.datasource.remote.responses.ApiResponse
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.repository.ClassesRepository
import com.example.ceban.core.repository.UserRepository

class AssignmentViewModel(private val repository: ClassesRepository, private val userRepository: UserRepository): ViewModel() {
    fun getAssignment(subjectId: Int, level: String, studentId: Int?): LiveData<ApiResponse<List<AssignmentResponseItem>>> =
        repository.getAssignmentBySubjectId(subjectId, level, studentId)

    fun getUser() = userRepository.getUser()
}