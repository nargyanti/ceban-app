package com.example.ceban.core.datasource.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceban.core.datasource.remote.api.ClassesService
import com.example.ceban.core.datasource.remote.responses.ClassesResponse
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

    fun getAll(): LiveData<ClassesResponse> {
        val liveData = MutableLiveData<ClassesResponse>()
        classesService.getAllSubjects().enqueue(object : Callback<ClassesResponse> {
            override fun onResponse(
                call: Call<ClassesResponse>,
                response: Response<ClassesResponse>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if(data != null) {
                        liveData.postValue(data)
                    }
                }
            }

            override fun onFailure(call: Call<ClassesResponse>, t: Throwable) {
                Log.e("ClassesApi", "Error: ${t.message}")
            }

        })
        return liveData
    }
}