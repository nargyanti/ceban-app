package com.example.ceban

data class Assignment(
    var id: Int = 0,
    var subjectId: Int = 0,
    var name: String = "",
    var dueDateTime: String = "",
    var question: String = "",
)