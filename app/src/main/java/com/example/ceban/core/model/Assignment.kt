package com.example.ceban.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assignment(
    var id: Int = 0,
    var subjectId: Int = 0,
    var name: String = "",
    var dueDateTime: String = "",
    var question: String = "",
): Parcelable