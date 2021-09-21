package com.example.ceban.ui.assignment.detail.guru.studentanswer.form

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.requests.AnswerRequest
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.datasource.remote.responses.AssignmentStudentResponse
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.core.model.Student
import com.example.ceban.databinding.AssignmentFileDialogBinding
import com.example.ceban.databinding.FragmentTeacherFormBinding
import com.example.ceban.ui.assignment.detail.AssignmentDetailActivity
import com.example.ceban.ui.assignment.detail.guru.studentanswer.StudentAnswerActivity
import com.example.ceban.ui.assignment.detail.guru.studentanswer.StudentAnswerViewModel
import com.example.ceban.ui.assignment.detail.guru.studentanswer.answer.AnswerFragment
import com.example.ceban.ui.assignment.detail.siswa.AttachmentToAddAdapter
import com.example.ceban.ui.assignment.detail.siswa.submission.home.SubmissionActivity
import com.example.ceban.utils.Attachment
import com.example.ceban.utils.ViewModelFactory
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STUDENT = "student"
private const val ASSIGNMENT = "assignment"

/**
 * A simple [Fragment] subclass.
 * Use the [TeacherFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeacherFormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var student: AssignmentStudentResponse? = null
    private var assignment: AssignmentResponseItem? = null

    private var fileListToAdd = ArrayList<Attachment>()
    private lateinit var attachmentToAddAdapter: AttachmentToAddAdapter

    private lateinit var binding: FragmentTeacherFormBinding
    private lateinit var viewModel: StudentAnswerViewModel

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
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeacherFormBinding.inflate(layoutInflater, container, false)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory)[StudentAnswerViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null) {
            binding.btnUploadAnswer.setOnClickListener {
                openAddFileDialog()
            }
            binding.btnSubmit.setOnClickListener {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val date = dateFormat.format(Date())
                val request = AnswerRequest(assignmentId = assignment?.id, 0, student?.userId, date)
                val uploadStatus = arrayListOf<Boolean>()
                viewModel.addAnswer(request).observe(viewLifecycleOwner) { answer ->
                    when(answer.status) {
                        StatusResponse.SUCCESS -> {
                            viewModel.fileList.observe(viewLifecycleOwner) { fileList ->
                                fileList.forEach {
                                    student?.userId?.let { studentId ->
                                        viewModel.addAnswerPictures(it.file, answer.body.id).observe(viewLifecycleOwner) {
                                            when(it.status) {
                                                StatusResponse.SUCCESS -> {
                                                    Toast.makeText(context, "Berhasil mengunggah gambar", Toast.LENGTH_SHORT).show()
                                                    uploadStatus.add(true)

                                                    if (uploadStatus.size == fileList.size && uploadStatus.all { it }) {
                                                        val fragment = AnswerFragment().apply {
                                                            arguments = Bundle().apply {
                                                                putParcelable(AnswerFragment.ANSWER, answer.body)
                                                            }
                                                        }
                                                        requireActivity().supportFragmentManager.beginTransaction()
                                                            .replace(R.id.student_answer_container, fragment)
                                                            .commit()
                                                    }
                                                }
                                                else -> {
                                                    Toast.makeText(context, "Terjadi kesalahan saat mengunggah gambar", Toast.LENGTH_SHORT).show()
                                                    uploadStatus.add(false)
                                                }
                                            }
                                        }
                                    }


                                }
                            }
                        }
                    }
                }

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(student: AssignmentStudentResponse?, assignment: AssignmentResponseItem?) =
            TeacherFormFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(STUDENT, student)
                    putParcelable(ASSIGNMENT, assignment)
                }
            }
    }

    private fun openAddFileDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        val addFileBinding: AssignmentFileDialogBinding =
            AssignmentFileDialogBinding.inflate(layoutInflater)
        builder.setView(addFileBinding.root)

        addFileBinding.btnAddFile.setOnClickListener {
            val chooseFile = Intent(Intent.ACTION_PICK)
            chooseFile.type = "image/*"
            val intent = Intent.createChooser(chooseFile, "Choose a File")
            startActivityForResult(intent, AssignmentDetailActivity.REQUEST_CODE)
        }

        addFileBinding.rvAssignment.layoutManager = LinearLayoutManager(context)
        attachmentToAddAdapter = AttachmentToAddAdapter()
        addFileBinding.rvAssignment.adapter = attachmentToAddAdapter
        attachmentToAddAdapter.setData(fileListToAdd)

        builder.setCancelable(true)
        val alertDialog = builder.create()
        addFileBinding.btnSubmit.setOnClickListener {
            viewModel.setFileList(fileListToAdd)
            alertDialog.cancel()
        }
        alertDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AssignmentDetailActivity.REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                Log.d("GetFile", "Path : ${uri.path}")
                val inputStream =  activity?.contentResolver?.openInputStream(uri)
                val file = File(File(activity?.filesDir, "photos"), "3.jpg")
                if (file.exists()) file.delete() else file.parentFile.mkdirs()
                val outputStream = FileOutputStream(file)
                if (inputStream != null) {
                    val buf = ByteArray(8192)
                    var length: Int
                    while (inputStream.read(buf).also { length = it } > 0) {
                        outputStream.write(buf, 0, length)
                    }
                }

                Log.d("Get File", "File name: ${file.name}")

                val attachment = Attachment(file.name, file)
                fileListToAdd.add(attachment)
                attachmentToAddAdapter.setData(fileListToAdd)
            }
        }
    }
}