package com.example.asystentnauczyciela.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.entities.Student
import com.example.asystentnauczyciela.viewmodels.AssignStudentsToSubjectViewModel

class AssignStudentsToSubjectListAdapter(
    private val students: LiveData<List<Student>>,
    private val viewModel: AssignStudentsToSubjectViewModel
) : RecyclerView.Adapter<AssignStudentsToSubjectListAdapter.AssignStudentsToSubjectListHolder>() {
    inner class AssignStudentsToSubjectListHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        val textViewAssignStudentStudentFirstName =
            view.findViewById<TextView>(R.id.assign_student_row_first_name)
        val textViewAssignStudentStudentLastName =
            view.findViewById<TextView>(R.id.assign_student_row_last_name)
        val buttonAssignStudent =
            view.findViewById<Button>(R.id.button_assign_student_row)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssignStudentsToSubjectListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.assign_student_row, parent, false)
        return AssignStudentsToSubjectListHolder(view)
    }

    override fun onBindViewHolder(holder: AssignStudentsToSubjectListHolder, position: Int) {
        val student = students.value?.get(position)

        holder.textViewAssignStudentStudentFirstName.text = student?.firstName
        holder.textViewAssignStudentStudentLastName.text = student?.lastName


        holder.buttonAssignStudent.setOnClickListener {
            //TODO PRZYPISAC
            student?.let {
                viewModel.assignStudentToSubject(it)
            }
        }
    }

    override fun getItemCount(): Int = students.value?.size ?: 0
}