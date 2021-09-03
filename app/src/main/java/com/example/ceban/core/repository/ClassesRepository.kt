package com.example.ceban.core.repository

import com.example.ceban.core.datasource.remote.ClassesRemoteDataSource

class ClassesRepository(private val classesRemoteDataSource: ClassesRemoteDataSource) {
    companion object {
        private var instance: ClassesRepository? = null
        fun getInstance(remoteDataSource: ClassesRemoteDataSource): ClassesRepository = instance ?: synchronized(this) {
            instance ?: ClassesRepository(remoteDataSource).apply { instance = this }
        }
    }

    fun getAllClasses() = classesRemoteDataSource.getAll()
}