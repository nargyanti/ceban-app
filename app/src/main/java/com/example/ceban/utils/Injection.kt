package com.example.ceban.utils

import android.content.Context
import com.example.ceban.core.datasource.local.UserLocalDataSource
import com.example.ceban.core.datasource.local.preferences.UserPreferences
import com.example.ceban.core.datasource.remote.ClassesRemoteDataSource
import com.example.ceban.core.datasource.remote.UserRemoteDataSource
import com.example.ceban.core.datasource.remote.api.ClassesService
import com.example.ceban.core.datasource.remote.api.UserService
import com.example.ceban.core.repository.ClassesRepository
import com.example.ceban.core.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Injection {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://4780-125-166-5-210.ngrok.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideUserRepository(context: Context): UserRepository {
        val userPreferences = UserPreferences(context)
        val userService = retrofit.create(UserService::class.java)
        val userLocalDataSource = UserLocalDataSource.getInstance(userPreferences)
        val userRemoteDataSource = UserRemoteDataSource.getInstance(userService)

        return UserRepository.getInstance(userLocalDataSource, userRemoteDataSource)
    }

    fun provideClassesRepository(): ClassesRepository {
        val service: ClassesService = retrofit.create(ClassesService::class.java)
        val classesRemoteDataSource = ClassesRemoteDataSource.getInstance(service)

        return ClassesRepository.getInstance(classesRemoteDataSource)
    }

}