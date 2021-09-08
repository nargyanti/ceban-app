package com.example.ceban.core.datasource.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceban.core.datasource.remote.api.ClassesService
import com.example.ceban.core.datasource.remote.requests.AnswerRequest
import com.example.ceban.core.datasource.remote.requests.SubjectRequest
import com.example.ceban.core.datasource.remote.responses.*
import com.example.ceban.utils.MultipartHelper
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

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

    fun getAssignmentBySubjectId(id: Int, level: String, studentId: Int?): LiveData<ApiResponse<List<AssignmentResponseItem>>> {
        val liveData = MutableLiveData<ApiResponse<List<AssignmentResponseItem>>>()

        classesService.getAssignmentBySubjectId(id, level, studentId).enqueue(object : Callback<List<AssignmentResponseItem>> {
            override fun onResponse(
                call: Call<List<AssignmentResponseItem>>,
                response: Response<List<AssignmentResponseItem>>
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

            override fun onFailure(call: Call<List<AssignmentResponseItem>>, t: Throwable) {
                liveData.postValue(ApiResponse.error("Error: ${t.message}", arrayListOf()))
            }

        })

        return liveData
    }

    fun getStudentFromAssignment(id: Int): LiveData<ApiResponse<List<AssignmentStudentResponse>>> {
        val liveData = MutableLiveData<ApiResponse<List<AssignmentStudentResponse>>>()

        classesService.getStudentByAssignmentId(id).enqueue(object : Callback<List<AssignmentStudentResponse>> {
            override fun onResponse(
                call: Call<List<AssignmentStudentResponse>>,
                response: Response<List<AssignmentStudentResponse>>
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

            override fun onFailure(call: Call<List<AssignmentStudentResponse>>, t: Throwable) {
                liveData.postValue(ApiResponse.error("Error: ${t.message}", arrayListOf()))
            }

        })

        return liveData
    }

    fun addAnswer(request: AnswerRequest): LiveData<ApiResponse<AnswerResponse>> {
        val liveData = MutableLiveData<ApiResponse<AnswerResponse>>()
        classesService.addAnswer(request).enqueue(object : Callback<AnswerResponse> {
            override fun onResponse(
                call: Call<AnswerResponse>,
                response: Response<AnswerResponse>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if(data != null) {
                        liveData.value =  ApiResponse.success(data)
                    }
                }else{
                    liveData.value =  ApiResponse.error(response.message(), AnswerResponse())
                }
            }

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                liveData.value =  ApiResponse.error(t.message, AnswerResponse())
            }

        })
        return liveData
    }

    fun addAnswerPictures(file: File?, answerId: Int): LiveData<ApiResponse<AnswerPictureResponse>> {
        val fileToUpload = MultipartHelper.getPartFromFile(file)

        val liveData = MutableLiveData<ApiResponse<AnswerPictureResponse>>()
        classesService.addAnswerPictures(fileToUpload, answerId).enqueue(object : Callback<AnswerPictureResponse> {
            override fun onResponse(
                call: Call<AnswerPictureResponse>,
                response: Response<AnswerPictureResponse>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if(data != null) {
                        liveData.value =  ApiResponse.success(data)
                    }
                }else{
                    liveData.value =  ApiResponse.error(response.message(), AnswerPictureResponse())
                }
            }

            override fun onFailure(call: Call<AnswerPictureResponse>, t: Throwable) {
                liveData.value =  ApiResponse.error(t.message, AnswerPictureResponse())
            }

        })

        return liveData
    }

    fun getAnswerPictures(id: Int): LiveData<ApiResponse<List<AnswerPictureResponse>>> {
        val liveData = MutableLiveData<ApiResponse<List<AnswerPictureResponse>>>()
        classesService.getAnswerPictures(id).enqueue(object : Callback<List<AnswerPictureResponse>> {
            override fun onResponse(
                call: Call<List<AnswerPictureResponse>>,
                response: Response<List<AnswerPictureResponse>>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if(data != null) {
                        liveData.value =  ApiResponse.success(data)
                    }
                }else{
                    liveData.value =  ApiResponse.error(response.message(), arrayListOf())
                }
            }

            override fun onFailure(call: Call<List<AnswerPictureResponse>>, t: Throwable) {
                liveData.value =  ApiResponse.error(t.message, arrayListOf())
            }

        })
        return liveData
    }

    fun deleteAnswerPictures(id: Int): LiveData<ApiResponse<MessageResponse>> {
        val liveData = MutableLiveData<ApiResponse<MessageResponse>>()
        classesService.deleteAnswerPicture(id).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(
                call: Call<MessageResponse>,
                response: Response<MessageResponse>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if(data != null) {
                        liveData.value =  ApiResponse.success(data)
                    }
                }else{
                    liveData.value =  ApiResponse.error(response.message(), MessageResponse())
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                liveData.value =  ApiResponse.error(t.message, MessageResponse())
            }

        })
        return liveData
    }
}