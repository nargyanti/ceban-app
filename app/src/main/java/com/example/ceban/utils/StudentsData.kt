package com.example.ceban.utils

import com.example.ceban.core.model.Student

object StudentsData {
    private val studentId = arrayOf(
        1,
        2,
        3,
        4,
        5
    )

    private val studentNames = arrayOf(
        "Abdul Rohman",
        "Elvira Sania",
        "Nabilah Argyanti",
        "Naufal Nafidiin",
        "Widiareta Safitri"
    )

    private val studentNo = arrayOf(
        1,
        2,
        3,
        4,
        5
    )

    private val studentClassName = arrayOf(
        "XII-MIA 1",
        "XII-MIA 1",
        "XII-MIA 1",
        "XII-MIA 1",
        "XII-MIA 1"
    )


    val listData: ArrayList<Student>
        get() {
            val list = arrayListOf<Student>()
            for (position in studentNames.indices) {
                val student = Student()
                student.id = studentId[position]
                student.name = studentNames[position]
                student.className = studentClassName[position]
                student.no = studentNo[position]
                list.add(student)
            }
            return list
        }
}