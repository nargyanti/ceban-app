package com.example.ceban.ui.assignment.detail.guru.studentanswer.answer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.requests.AnswerRequest
import com.example.ceban.core.datasource.remote.responses.*
import com.example.ceban.databinding.FragmentAnswerBinding
import com.example.ceban.ui.assignment.detail.guru.studentanswer.ImageAdapter
import com.example.ceban.ui.assignment.detail.guru.studentanswer.StudentAnswerViewModel
import com.example.ceban.utils.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [AnswerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnswerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var student: AssignmentStudentResponse? = null
    private var answer: AnswerResponse? = null
    private lateinit var binding: FragmentAnswerBinding
    private lateinit var viewModel: StudentAnswerViewModel
    var imageList = arrayListOf<String>()
    var jawaban = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            student = it.getParcelable(STUDENT)
            answer = it.getParcelable(ANSWER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAnswerBinding.inflate(inflater, container, false)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[StudentAnswerViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(student != null) {
            student?.answerId?.let { answerId ->
                prepareView(answerId)
            }
        }else if(answer != null) {
            answer?.id?.let { id ->
                prepareView(id)
            }
        }
    }

    fun prepareView(answerId: Int) {
        viewModel.getAnswer(answerId).observe(viewLifecycleOwner) { response ->
            when(response.status) {
                StatusResponse.SUCCESS -> {
                    answer = response.body

                    viewModel.getPictures(answerId).observe(viewLifecycleOwner) { response ->
                        when(response.status) {
                            StatusResponse.SUCCESS -> {
                                jawaban = ""
                                response.body.forEach { pictures ->
                                    imageList.add( pictures.path)
                                    jawaban += pictures.convertResult + "\n"
                                }
                                binding.tvStudentAnswer.text = jawaban
                                prepareImage()
                            }
                        }
                    }

                    binding.edtScore.setText("${answer?.score}")
                    binding.btnSaveScore.setOnClickListener {
                        editScore(response.body.id)
                    }
                }
            }
        }

    }

    fun editScore(answerId: Int) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = dateFormat.parse(answer?.submitDatetime)
        val validDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val validDate = validDateFormat.format(date)
        val request = AnswerRequest(
            assignmentId = answer?.assignmentId,
            score = binding.edtScore.text.toString().toInt(),
            studentId = answer?.studentId,
            submitDatetime = validDate
        )
        viewModel.editScore(request, answerId).observe(viewLifecycleOwner) { response ->
            when(response.status) {
                StatusResponse.SUCCESS -> {
                    Toast.makeText(requireActivity(), "Berhasil mengubah jawaban", Toast.LENGTH_SHORT).show()
                    prepareView(response.body.id)
                }
            }
        }
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

        const val STUDENT = "student"
        const val ASSIGNMENT = "assignment"
        const val ANSWER = "answer"
    }


}