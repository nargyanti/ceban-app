package com.example.ceban.core.datasource.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AssignmentResponse(

	@field:SerializedName("AssignmentResponse")
	val assignmentResponse: List<AssignmentResponseItem>
) : Parcelable

@Parcelize
data class AssignmentResponseItem(

	@field:SerializedName("subject_id")
	val subjectId: Int,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("due_datetime")
	val dueDatetime: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable
