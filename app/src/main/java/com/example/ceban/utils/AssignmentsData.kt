package com.example.ceban.utils

import com.example.ceban.core.model.Assignment

object AssignmentsData {
    private val assignmentId = arrayOf(
        1,
        2,
        3
    )

    private val assignmentSubjectId = arrayOf(
        1,
        2,
        3
    )

    private val assignmentNames = arrayOf(
        "Tugas Penjaskes Halaman 120",
        "Tugas Halaman 15",
        "Tugas Halaman 20",
    )

    private val assignmentDueDateTimes = arrayOf(
        "11 Agustus 2021 23.59",
        "12 Agustus 2021 23.59",
        "13 Agustus 2021 23.59"
    )

    private val assignmentQuestions = arrayOf(
        "Jelaskan secara singkat sejarah tenis meja. Tulis di buku tulis minimal 1/2 halaman. Kumpulkan maksimal 11 Agustus 2021 pukul 23.59",
        "1. Sebutkan jenis-jenis pernafasan",
        "1. Sebutkan jenis-jenis pernafasan"
    )

    //    val listData: ArrayList<Assignment>
//        get() {
//            val list = arrayListOf<Assignment>()
//            for (position in AssignmentsData.assignmentNames.indices) {
//                val assignment = Assignment()
//                assignment.id = AssignmentsData.assignmentId[position]
//                assignment.subjectId = AssignmentsData.assignmentSubjectId[position]
//                assignment.name = AssignmentsData.assignmentNames[position]
//                assignment.dueDateTime = AssignmentsData.assignmentDueDateTimes[position]
//                list.add(assignment)
//            }
//            return list
//        }

    fun listData(EXTRA_SUBJECTID: Int): ArrayList<Assignment> {
        val list = arrayListOf<Assignment>()
        for (position in assignmentNames.indices) {
            if(assignmentId[position] == EXTRA_SUBJECTID) {
                val assignment = Assignment()
                assignment.id = assignmentId[position]
                assignment.subjectId = assignmentSubjectId[position]
                assignment.name = assignmentNames[position]
                assignment.dueDateTime = assignmentDueDateTimes[position]
                assignment.question = assignmentQuestions[position]
                list.add(assignment)
            }
        }
        return list
    }


}