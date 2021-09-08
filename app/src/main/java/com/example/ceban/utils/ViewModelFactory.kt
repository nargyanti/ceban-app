package com.example.ceban.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ceban.core.repository.ClassesRepository
import com.example.ceban.core.repository.UserRepository
import com.example.ceban.ui.assignment.detail.AssignmentDetailViewModel
import com.example.ceban.ui.assignment.detail.guru.studentanswer.StudentAnswerViewModel
import com.example.ceban.ui.assignment.detail.siswa.submission.edit.SubmissionEditViewModel
import com.example.ceban.ui.assignment.detail.siswa.submission.home.AnswerViewModel
import com.example.ceban.ui.assignment.home.AssignmentViewModel
import com.example.ceban.ui.login.LoginViewModel
import com.example.ceban.ui.main.ClassesViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
    private val classesRepository: ClassesRepository):
    ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideUserRepository(context), Injection.provideClassesRepository()).apply {
                instance = this
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AssignmentDetailViewModel::class.java) -> {
                AssignmentDetailViewModel(classesRepository, userRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(ClassesViewModel::class.java) -> {
                ClassesViewModel(classesRepository, userRepository) as T
            }
            modelClass.isAssignableFrom(AssignmentViewModel::class.java) -> {
                AssignmentViewModel(classesRepository, userRepository) as T
            }
            modelClass.isAssignableFrom(StudentAnswerViewModel::class.java) -> {
                StudentAnswerViewModel() as T
            }
            modelClass.isAssignableFrom(AnswerViewModel::class.java) -> {
                AnswerViewModel(classesRepository) as T
            }
            modelClass.isAssignableFrom(SubmissionEditViewModel::class.java) -> {
                SubmissionEditViewModel(classesRepository, userRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}