package com.example.ceban

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
        "Tugas Halaman 110",
        "Tugas Halaman 111",
        "Tugas Halaman 112",
    )

    private val assignmentDueDateTimes = arrayOf(
        "11 Agustus 2021 23.59",
        "12 Agustus 2021 23.59",
        "13 Agustus 2021 23.59"
    )

    private val assignmentQuestions = arrayOf(
        "1. Sebutkan jenis-jenis pernafasan\n2. Sebutkan rasa yang pernah ada",
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
        for (position in AssignmentsData.assignmentNames.indices) {
            if(AssignmentsData.assignmentId[position] == EXTRA_SUBJECTID) {
                val assignment = Assignment()
                assignment.id = AssignmentsData.assignmentId[position]
                assignment.subjectId = AssignmentsData.assignmentSubjectId[position]
                assignment.name = AssignmentsData.assignmentNames[position]
                assignment.dueDateTime = AssignmentsData.assignmentDueDateTimes[position]
                assignment.question = AssignmentsData.assignmentQuestions[position]
                list.add(assignment)
            }
        }
        return list
    }


}