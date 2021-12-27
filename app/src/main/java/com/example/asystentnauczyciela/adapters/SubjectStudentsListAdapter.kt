package com.example.asystentnauczyciela.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.entities.SubjectWithStudents

class SubjectStudentsListAdapter(private val subjectWithStudents: LiveData<SubjectWithStudents>) :
    RecyclerView.Adapter<SubjectStudentsListAdapter.SubjectStudentsListHolder>() {

    inner class SubjectStudentsListHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewStudentFirstName =
            view.findViewById<TextView>(R.id.subject_student_row_first_name)
        val textViewStudentLastName =
            view.findViewById<TextView>(R.id.subject_student_row_last_name)
        val buttonStudentGradesInfo =
            view.findViewById<Button>(R.id.subject_student_row_grades_info)

        fun navigateToSubjectStudentGrades(albumNumber: Long?, subjectId: Long?) {
            val bundle = bundleOf(Pair("albumNumber", albumNumber), Pair("subjectId", subjectId))

            view.findNavController()
                .navigate(R.id.action_subjectInfoFragment_to_subjectStudentGradesFragment, bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectStudentsListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_student_row, parent, false)
        return SubjectStudentsListHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectStudentsListHolder, position: Int) {
        val subjectId = subjectWithStudents.value?.subject?.subjectId
        val student = subjectWithStudents.value?.students?.get(position)

        holder.textViewStudentFirstName.text = student?.firstName
        holder.textViewStudentLastName.text = student?.lastName


        holder.buttonStudentGradesInfo.setOnClickListener {
            holder.navigateToSubjectStudentGrades(student?.albumNumber, subjectId)
        }
    }

    override fun getItemCount(): Int = subjectWithStudents.value?.students?.size ?: 0
}