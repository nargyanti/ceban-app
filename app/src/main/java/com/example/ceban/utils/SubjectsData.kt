package com.example.ceban.utils

import com.example.ceban.core.model.Subject

object SubjectsData {
    private val subjectId = arrayOf(
        1,
        2,
        3,
        4
    )

    private val subjectNames = arrayOf(
        "Penjaskes",
        "Bahasa Inggris",
        "IPA",
        "Matematika"
    )

    private val subjectClass = arrayOf(
        "XII-MIA 1",
        "XII-MIA 1",
        "XII-MIA 1",
        "XII-MIA 1"
    )

    private val subjectPassword = arrayOf(
        "AXCF32",
        "B93PS2",
        "A9JA12",
        "K21HS2",
    )

    private val subjectTeacher = arrayOf(
        "Hendra Irawan",
        "Rina Kartika",
        "Agus Kuncoro",
        "Sella Andriani"
    )

    private val subjectStudentAmount = arrayOf(
        26,
        21,
        29,
        23
    )

    private val subjectAssignmentAmount = arrayOf(
        7,
        6,
        3,
        5
    )

    val listData: ArrayList<Subject>
        get() {
            val list = arrayListOf<Subject>()
            for (position in subjectNames.indices) {
                val subject = Subject()
                subject.id = subjectId[position]
                subject.name = subjectNames[position]
                subject.className = subjectClass[position]
                subject.password = subjectPassword[position]
                subject.teacher = subjectTeacher[position]
                subject.assignmentAmount = subjectAssignmentAmount[position]
                subject.studentAmount = subjectStudentAmount[position]
                list.add(subject)
            }
            return list
        }
}