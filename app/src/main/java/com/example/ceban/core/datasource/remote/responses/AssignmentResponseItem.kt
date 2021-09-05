package com.example.ceban.core.datasource.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AssignmentResponseItem(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("due_datetime")
	val dueDatetime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("answer_count")
	val answerCount: Int = 0
) : Parcelable
