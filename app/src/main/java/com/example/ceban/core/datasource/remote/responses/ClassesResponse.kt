package com.example.ceban.core.datasource.remote.responses

import com.google.gson.annotations.SerializedName

data class ClassesResponse(

	@field:SerializedName("ClassesResponse")
	val classesResponse: List<ClassesResponseItem>
)

data class ClassesResponseItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("class_name")
	val className: String
)
