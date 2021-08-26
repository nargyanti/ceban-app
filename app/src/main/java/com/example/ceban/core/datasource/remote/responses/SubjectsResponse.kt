package com.example.ceban.core.datasource.remote.responses

import com.google.gson.annotations.SerializedName

data class SubjectsResponse(

	@field:SerializedName("SubjectsResponse")
	val subjectsResponse: List<SubjectsResponseItem>
)

data class SubjectsResponseItem(

	@field:SerializedName("teacher_id")
	val teacherId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("school_year")
	val schoolYear: String
)
