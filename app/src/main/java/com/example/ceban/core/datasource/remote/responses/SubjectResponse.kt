package com.example.ceban.core.datasource.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubjectResponse(

	@field:SerializedName("teacher_name")
	val teacherName: String? = null,

	@field:SerializedName("student_count")
	val studentCount: Int? = null,

	@field:SerializedName("teacher_id")
	val teacherId: Int? = null,

	@field:SerializedName("class_id")
	val classId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("school_year")
	val schoolYear: String? = null,

	@field:SerializedName("class_name")
	val className: String? = null,

	@field:SerializedName("assignment_count")
	val assignmentCount: Int? = null
) : Parcelable
