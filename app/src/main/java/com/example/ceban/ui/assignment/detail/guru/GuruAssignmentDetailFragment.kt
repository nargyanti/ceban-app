package com.example.ceban.ui.assignment.detail.guru

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.core.model.Assignment
import com.example.ceban.databinding.FragmentGuruAssignmentDetailBinding
import com.example.ceban.ui.assignment.detail.AssignmentDetailViewModel
import com.example.ceban.utils.StudentsData
import com.example.ceban.utils.ViewModelFactory

private const val EXTRA_ASSIGNMENT = "extra_assignment"
class GuruAssignmentDetailFragment : Fragment() {
    private var assignment: AssignmentResponseItem? = null
    private lateinit var binding: FragmentGuruAssignmentDetailBinding
    private lateinit var viewModel: AssignmentDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            assignment = it.getParcelable(EXTRA_ASSIGNMENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuruAssignmentDetailBinding.inflate(inflater, container, false)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory)[AssignmentDetailViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
//            TODO("mengisi data dengan data dari API")
            assignment?.let {
                viewModel.getStudentAnswerFromAssignment(it.id).observe(viewLifecycleOwner) { studentList ->
                    when(studentList.status) {
                        StatusResponse.SUCCESS -> {
                            binding.rvStudentList.layoutManager = LinearLayoutManager(requireActivity())
                            val adapter = CardViewStudentListAdapter(assignment, studentList.body, requireActivity())
                            binding.rvStudentList.adapter = adapter
                        }
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GuruAssignmentDetailActivity.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: AssignmentResponseItem) =
            GuruAssignmentDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_ASSIGNMENT, param1)
                }
            }
    }
}