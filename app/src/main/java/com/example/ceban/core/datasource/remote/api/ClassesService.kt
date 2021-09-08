package com.example.ceban.core.datasource.remote.api

import com.example.ceban.core.datasource.remote.requests.AnswerRequest
import com.example.ceban.core.datasource.remote.requests.SubjectRequest
import com.example.ceban.core.datasource.remote.responses.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ClassesService {
    @GET("users/{id}/subjects")
    fun getAllSubjects(@Path("id") id: Int, @Query("level") level: String): Call<List<SubjectsResponseItem>>

    @GET("subjects/{id}/assignments")
    fun getAssignmentBySubjectId(@Path("id") id: Int, @Query("level")level: String, @Query("studentId") studentId: Int?): Call<List<AssignmentResponseItem>>

    @GET("assignments/{id}/students")
    fun getStudentByAssignmentId(@Path("id") id: Int): Call<List<AssignmentStudentResponse>>

    @POST("answers")
    fun addAnswer(@Body request: AnswerRequest): Call<AnswerResponse>

    @Multipart
    @POST("answer_pictures")
    fun addAnswerPictures(
        @Part path: MultipartBody.Part?,
        @Part("answer_id") answer_id: Int
    ): Call<AnswerPictureResponse>

    @GET("answers/{id}/detail")
    fun getAnswerPictures(
        @Path("id") id: Int
    ): Call<List<AnswerPictureResponse>>

    @DELETE("answer_pictures/{id}")
    fun deleteAnswerPicture(@Path("id") pictureId: Int): Call<MessageResponse>
}