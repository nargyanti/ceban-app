package com.example.ceban

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
            for (position in StudentsData.studentNames.indices) {
                val student = Student()
                student.id = StudentsData.studentId[position]
                student.name = StudentsData.studentNames[position]
                student.className = StudentsData.studentClassName[position]
                student.no = StudentsData.studentNo[position]
                list.add(student)
            }
            return list
        }
}