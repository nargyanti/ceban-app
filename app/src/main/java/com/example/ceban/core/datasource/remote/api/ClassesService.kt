package com.example.ceban.core.datasource.remote.api

import com.example.ceban.core.datasource.remote.responses.SubjectsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClassesService {
    @GET("users/{id}/subjects")
    fun getAllSubjects(@Path("id") id: Int): Call<SubjectsResponse>



}