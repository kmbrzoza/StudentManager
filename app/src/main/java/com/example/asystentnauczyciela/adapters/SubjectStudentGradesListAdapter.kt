package com.example.asystentnauczyciela.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.entities.Grade

class SubjectStudentGradesListAdapter(private val grades: LiveData<List<Grade>>) :
    RecyclerView.Adapter<SubjectStudentGradesListAdapter.SubjectStudentGradesListHolder>() {

    inner class SubjectStudentGradesListHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        val textViewSubjectStudentGradeGrade: TextView =
            view.findViewById<TextView>(R.id.subject_student_grade_row_grade)
        val textViewSubjectStudentGradeWeight: TextView =
            view.findViewById<TextView>(R.id.subject_student_grade_row_weight)
        val textViewSubjectStudentGradeDescription: TextView =
            view.findViewById<TextView>(R.id.subject_student_grade_row_description)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectStudentGradesListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_student_grade_row, parent, false)
        return SubjectStudentGradesListHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectStudentGradesListHolder, position: Int) {
        val grade = grades.value?.get(position)

        holder.textViewSubjectStudentGradeGrade.text = grade?.getGradeStr()
        holder.textViewSubjectStudentGradeWeight.text = grade?.getWeightStr()
        holder.textViewSubjectStudentGradeDescription.text = grade?.description
    }

    override fun getItemCount(): Int = grades.value?.size ?: 0
}