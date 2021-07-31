package com.example.ceban.core.model

data class Assignment(
    var id: Int = 0,
    var subjectId: Int = 0,
    var name: String = "",
    var dueDateTime: String = "",
    var question: String = "",
)