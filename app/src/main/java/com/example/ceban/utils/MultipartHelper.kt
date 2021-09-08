package com.example.ceban.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object MultipartHelper {
    fun getPartFromFile(file: File?): MultipartBody.Part? {
        return file?.let {
            MultipartBody.Part.createFormData(
                "path",
                file.name,
                it.asRequestBody("image/*".toMediaTypeOrNull())
            )
        }
    }
}