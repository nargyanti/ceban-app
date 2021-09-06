package com.example.ceban.core.datasource.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AssignmentStudentResponse(

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("no_absen")
	val noAbsen: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("answer_id")
	val answerId: Int? = null
) : Parcelable
