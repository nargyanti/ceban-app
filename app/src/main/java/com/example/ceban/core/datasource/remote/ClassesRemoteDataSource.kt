package com.example.ceban.core.datasource.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceban.core.datasource.remote.api.ClassesService
import com.example.ceban.core.datasource.remote.requests.SubjectRequest
import com.example.ceban.core.datasource.remote.responses.ApiResponse
import com.example.ceban.core.datasource.remote.responses.SubjectsResponseItem
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

    fun getAll(id: Int, level: String): LiveData<ApiResponse<List<SubjectsResponseItem>>> {
        val liveData = MutableLiveData<ApiResponse<List<SubjectsResponseItem>>>()
        classesService.getAllSubjects(id, level).enqueue(object : Callback<List<SubjectsResponseItem>> {
            override fun onResponse(
                call: Call<List<SubjectsResponseItem>>,
                response: Response<List<SubjectsResponseItem>>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        liveData.postValue(ApiResponse.success(data))
                    }
                }else{
                    liveData.postValue(ApiResponse.error("Error: ${response.message()}", arrayListOf()))
                }
            }

            override fun onFailure(call: Call<List<SubjectsResponseItem>>, t: Throwable) {
                liveData.postValue(ApiResponse.error("Error: ${t.message}", arrayListOf()))
            }

        })
        return liveData
    }
}