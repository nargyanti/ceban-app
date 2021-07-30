package com.example.ceban

object SubjectsData {
    private val subjectId = arrayOf(
        1,
        2,
        3,
        4
    )

    private val subjectNames = arrayOf(
        "Bahasa Indonesia",
        "Bahasa Inggris",
        "IPA",
        "Matematika"
    )

    private val subjectClass = arrayOf(
        "XII-MIA 1",
        "XII-MIA 2",
        "XII-MIA 3",
        "XII-MIA 4"
    )

    private val subjectPassword = arrayOf(
        "ABCD12",
        "ABCD12",
        "ABCD12",
        "ABCD12",
    )

    private val subjectTeacher = arrayOf(
        "Pak Ogah",
        "Pak Ogah",
        "Pak Ogah",
        "Pak Ogah"
    )

    private val subjectStudentAmount = arrayOf(
        10,
        20,
        30,
        35
    )

    private val subjectAssignmentAmount = arrayOf(
        10,
        20,
        30,
        40
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