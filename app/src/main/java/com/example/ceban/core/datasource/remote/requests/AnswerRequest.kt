package com.example.ceban.core.datasource.remote.requests

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnswerRequest(

	@field:SerializedName("assignment_id")
	val assignmentId: Int? = null,

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("student_id")
	val studentId: Int? = null,

	@field:SerializedName("submit_datetime")
	val submitDatetime: String? = null
) : Parcelable
