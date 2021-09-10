package com.example.ceban.core.datasource.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageResponse(

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
