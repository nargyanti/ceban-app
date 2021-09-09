package com.example.ceban.ui.assignment.detail.guru.studentanswer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ceban.core.datasource.remote.requests.AnswerRequest
import com.example.ceban.core.repository.ClassesRepository
import com.example.ceban.core.repository.UserRepository
import com.example.ceban.utils.Attachment
import java.io.File

class StudentAnswerViewModel(private val classesRepository: ClassesRepository,
                             private val userRepository: UserRepository): ViewModel() {
    private val _fileList: MutableLiveData<List<Attachment>> = MutableLiveData<List<Attachment>>()
    var fileList: LiveData<List<Attachment>> = _fileList
    fun setFileList(data: List<Attachment>) {
        _fileList.postValue(data)
    }

    fun addAnswerPictures(file: File?, answerId: Int) = classesRepository.addAnswerPictures(file, answerId)

    fun addAnswer(request: AnswerRequest) = classesRepository.addAnswer(request)

    fun getPictures(answerId: Int) = classesRepository.getAnswerPictures(answerId)
}