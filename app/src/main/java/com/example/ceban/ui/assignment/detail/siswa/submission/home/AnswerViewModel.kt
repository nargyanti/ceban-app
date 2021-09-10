package com.example.ceban.ui.assignment.detail.siswa.submission.home

import androidx.lifecycle.ViewModel
import com.example.ceban.core.repository.ClassesRepository

class AnswerViewModel(private val repository: ClassesRepository) : ViewModel() {
    fun getAnswerPictures(id: Int) = repository.getAnswerPictures(id)
}