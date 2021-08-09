package com.example.ceban.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    var id: Int = 0,
    var name: String = "",
    var no: Int = 0,
    var className: String = "",
): Parcelable