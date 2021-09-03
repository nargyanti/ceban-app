package com.example.ceban.core.datasource.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceban.core.datasource.remote.api.ClassesService
import com.example.ceban.core.datasource.remote.responses.ApiResponse
import com.example.ceban.core.datasource.remote.responses.SubjectsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassesRemoteDataSource private constructor(private val classesService: ClassesService) {
    companion object {
        private var instance: ClassesRemoteDataSource? = null

        fun getInstance(classesService: ClassesService): ClassesRemoteDataSource = instance ?: synchronized(this) {
            instance ?: ClassesRemoteDataSource(classesService).apply {
                instance = this
            }
        }
    }

    fun getAll(id: Int): LiveData<ApiResponse<SubjectsResponse>> {
        val liveData = MutableLiveData<ApiResponse<SubjectsResponse>>()
        classesService.getAllSubjects(id).enqueue(object : Callback<SubjectsResponse> {
            override fun onResponse(
                call: Call<SubjectsResponse>,
                response: Response<SubjectsResponse>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        liveData.postValue(ApiResponse.success(data))
                    }
                }else{
                    liveData.postValue(ApiResponse.error("Error: ${response.message()}", SubjectsResponse()))
                }
            }

            override fun onFailure(call: Call<SubjectsResponse>, t: Throwable) {
                liveData.postValue(ApiResponse.error("Error: ${t.message}", SubjectsResponse()))
            }

        })
        return liveData
    }
}