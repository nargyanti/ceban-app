package com.example.ceban.core.datasource.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class SubjectsResponseItem(

	@field:SerializedName("subject_id")
	val subjectId: Int? = null,

	@field:SerializedName("teacher_name")
	val teacherName: String? = null,

	@field:SerializedName("student_count")
	val studentCount: Int? = null,

	@field:SerializedName("teacher_id")
	val teacherId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("school_year")
	val schoolYear: String? = null,

	@field:SerializedName("assignment_count")
	val assignmentCount: Int? = null
) : Parcelable
