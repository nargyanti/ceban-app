package com.example.ceban.core.datasource.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntity(

	@field:SerializedName("password")
	var password: String? = null,

	@field:SerializedName("telp")
	var telp: String? = null,

	@field:SerializedName("level")
	var level: String? = null,

	@field:SerializedName("entry_year")
	var entryYear: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("username")
	var username: String? = null
) : Parcelable
