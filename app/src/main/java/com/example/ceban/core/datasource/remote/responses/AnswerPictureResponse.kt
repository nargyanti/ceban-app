package com.example.ceban.core.datasource.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnswerPictureResponse(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("convert_result")
	val convertResult: String? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("answer_id")
	val answerId: String? = null
) : Parcelable
