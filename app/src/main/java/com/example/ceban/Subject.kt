package com.example.ceban

data class Subject(
    var id: Int = 0,
    var name: String = "",
    var className: String = "",
    var password: String = "",
    var teacher: String = "",
    var assignmentAmount: Int = 0,
    var studentAmount: Int = 0
)