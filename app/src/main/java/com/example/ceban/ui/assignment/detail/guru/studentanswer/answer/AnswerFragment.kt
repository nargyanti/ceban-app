package com.example.ceban.ui.assignment.detail.guru.studentanswer.answer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.datasource.remote.responses.AssignmentStudentResponse
import com.example.ceban.databinding.FragmentAnswerBinding
import com.example.ceban.ui.assignment.detail.guru.studentanswer.ImageAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STUDENT = "student"
private const val ASSIGNMENT = "assignment"

/**
 * A simple [Fragment] subclass.
 * Use the [AnswerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnswerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var student: AssignmentStudentResponse? = null
    private var assignment: AssignmentResponseItem? = null
    private lateinit var binding: FragmentAnswerBinding
    var imageList = arrayListOf("https://cdn.idntimes.com/content-images/post/20200810/4-b85fc68aa0c39ec0d7cf8292b429ec09_600x400.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            student = it.getParcelable(STUDENT)
            assignment = it.getParcelable(ASSIGNMENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvStudentAnswer.text = resources.getString(R.string.student_answer)
        if (student != null) {
            student.let {
                binding.edtScore.setText("${it?.score}")
            }

        }
        prepareImage()
    }

    private fun prepareImage() {
        val adapter = ImageAdapter(requireActivity(), imageList)
        binding.vpStudentAnswer.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AnswerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(student: AssignmentStudentResponse?, assignment: AssignmentResponseItem?) =
            AnswerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(STUDENT, student)
                    putParcelable(ASSIGNMENT, assignment)
                }
            }
    }


}