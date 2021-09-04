package com.example.ceban.core.datasource.remote.requests

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubjectRequest(

	@field:SerializedName("level")
	val level: String
) : Parcelable
