package com.example.ceban.ui.assignment.detail.guru

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ceban.core.model.Assignment
import com.example.ceban.databinding.FragmentGuruAssignmentDetailBinding

private const val EXTRA_ASSIGNMENT = "extra_assignment"
class GuruAssignmentDetailFragment : Fragment() {
    private var assignment: Assignment? = null
    private lateinit var binding: FragmentGuruAssignmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            assignment = it.getParcelable(EXTRA_ASSIGNMENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuruAssignmentDetailBinding.inflate(inflater, container, false)
        return binding.root
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
        fun newInstance(param1: Assignment) =
            GuruAssignmentDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_ASSIGNMENT, param1)
                }
            }
    }
}