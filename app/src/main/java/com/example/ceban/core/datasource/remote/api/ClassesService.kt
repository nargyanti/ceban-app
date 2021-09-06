package com.example.ceban.core.datasource.remote.api

import com.example.ceban.core.datasource.remote.requests.SubjectRequest
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.datasource.remote.responses.AssignmentStudentResponse
import com.example.ceban.core.datasource.remote.responses.SubjectsResponseItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClassesService {
    @GET("users/{id}/subjects")
    fun getAllSubjects(@Path("id") id: Int, @Query("level") level: String): Call<List<SubjectsResponseItem>>

    @GET("subjects/{id}/assignments")
    fun getAssignmentBySubjectId(@Path("id") id: Int, @Query("level")level: String, @Query("studentId") studentId: Int?): Call<List<AssignmentResponseItem>>

    @GET("assignments/{id}/students")
    fun getStudentByAssignmentId(@Path("id") id: Int): Call<List<AssignmentStudentResponse>>
}