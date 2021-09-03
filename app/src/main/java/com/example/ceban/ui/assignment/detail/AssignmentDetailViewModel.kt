package com.example.ceban.ui.assignment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ceban.core.repository.UserRepository
import com.example.ceban.utils.Attachment

class AssignmentDetailViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _fileList: MutableLiveData<List<Attachment>> = MutableLiveData<List<Attachment>>()
    var fileList: LiveData<List<Attachment>> = _fileList
    fun setFileList(data: List<Attachment>) {
        _fileList.postValue(data)
    }

    fun getUser() = userRepository.getUser()

}