package com.example.ceban.ui.assignment.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.requests.AssignmentAddRequest
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.databinding.ActivityAssignmentAddBinding
import com.example.ceban.utils.ViewModelFactory
import com.mirfanrafif.myalarmmanager.DatePickerFragment
import com.mirfanrafif.myalarmmanager.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AssignmentAddActivity : AppCompatActivity(),
    DatePickerFragment.DialogDateListener,
    TimePickerFragment.DialogTimeListener {
    private lateinit var binding: ActivityAssignmentAddBinding
    private lateinit var viewModel: AssignmentAddViewModel
    private var tenggat = ""
    private var subjectId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentAddBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[AssignmentAddViewModel::class.java]
        subjectId = intent.getIntExtra(EXTRA_SUBJECT, 0)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.editTenggat.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
        }

        binding.btnAddAssignment.setOnClickListener {
            val judul = binding.editJudulTugas.text.toString()
            val tugas = binding.editTugas.text.toString()
            val tenggat = binding.editTenggat.text.toString()

            val request = AssignmentAddRequest(subjectId, tugas, tenggat, judul)
            viewModel.addAssignment(request).observe(this) {
                when(it.status) {
                    StatusResponse.SUCCESS -> {
                        Toast.makeText(this,
                            "Berhasil menambahkan tugas dengan judul ${it.body.name}",
                            Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    StatusResponse.ERROR -> {
                        Toast.makeText(this,
                            "Gagal menambahkan tugas : ${it.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        tenggat = dateFormat.format(calendar.time)

        val timePickerFragmentRepeat = TimePickerFragment()
        timePickerFragmentRepeat.show(supportFragmentManager, TIME_PICKER_TAG)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        tenggat += " " + dateFormat.format(calendar.time)

        binding.editTenggat.setText(tenggat)
    }

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_TAG = "TimePicker"
        const val EXTRA_SUBJECT = "extra_subject"
    }
}