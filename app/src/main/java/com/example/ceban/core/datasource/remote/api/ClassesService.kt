package com.example.ceban.core.datasource.remote.api

import com.example.ceban.core.datasource.remote.responses.ClassesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ClassesService {
    @GET("")
    fun getAllSubjects(): Call<ClassesResponse>



}