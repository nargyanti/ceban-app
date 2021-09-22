package com.example.ceban.core.repository

import com.example.ceban.core.datasource.remote.ClassesRemoteDataSource
import com.example.ceban.core.datasource.remote.requests.AnswerRequest
import com.example.ceban.core.datasource.remote.requests.AssignmentAddRequest
import com.example.ceban.core.datasource.remote.requests.SubjectRequest
import java.io.File

class ClassesRepository(private val classesRemoteDataSource: ClassesRemoteDataSource) {
    companion object {
        private var instance: ClassesRepository? = null
        fun getInstance(remoteDataSource: ClassesRemoteDataSource): ClassesRepository = instance ?: synchronized(this) {
            instance ?: ClassesRepository(remoteDataSource).apply { instance = this }
        }
    }

    fun getAllSubject(id: Int, level: String) = classesRemoteDataSource.getAll(id, level)

    fun getAssignmentBySubjectId(id: Int, level: String, studentId: Int?) = classesRemoteDataSource.getAssignmentBySubjectId(id, level, studentId)

    fun getStudentAnswerFromAssignment(id: Int) = classesRemoteDataSource.getStudentFromAssignment(id)

    fun addAnswer(request: AnswerRequest) = classesRemoteDataSource.addAnswer(request)

    fun addAnswerPictures(file: File?, answerId: Int) = classesRemoteDataSource.addAnswerPictures(file, answerId)

    fun getAnswerPictures(id: Int) = classesRemoteDataSource.getAnswerPictures(id)

    fun deleteAnswerPicture(id: Int) = classesRemoteDataSource.deleteAnswerPictures(id)

    fun getAnswerDetail(id: Int) = classesRemoteDataSource.getAnswer(id)
    fun editAnswer(answerRequest: AnswerRequest, answerId: Int) = classesRemoteDataSource.editAnswer(answerRequest, answerId)

    fun addAssignment(request: AssignmentAddRequest) = classesRemoteDataSource.addAssignment(request)
}