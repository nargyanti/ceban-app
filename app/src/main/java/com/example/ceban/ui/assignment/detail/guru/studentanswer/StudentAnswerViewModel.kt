package com.example.ceban.ui.assignment.detail.guru.studentanswer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ceban.utils.Attachment

class StudentAnswerViewModel: ViewModel() {
    private val _fileList: MutableLiveData<List<Attachment>> = MutableLiveData<List<Attachment>>()
    var fileList: LiveData<List<Attachment>> = _fileList
    fun setFileList(data: List<Attachment>) {
        _fileList.postValue(data)
    }
}