package com.example.ceban.core.datasource.remote.requests

import com.google.gson.annotations.SerializedName

data class AssignmentAddRequest(

	@field:SerializedName("subject_id")
	val subjectId: Int,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("due_datetime")
	val dueDatetime: String,

	@field:SerializedName("name")
	val name: String
)
