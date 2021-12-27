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
import com.example.asystentnauczyciela.entities.Subject
import com.example.asystentnauczyciela.R

class SubjectsListAdapter(private val subjects: LiveData<List<Subject>>) :
    RecyclerView.Adapter<SubjectsListAdapter.SubjectsListHolder>() {

    inner class SubjectsListHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewSubjectName: TextView = view.findViewById<TextView>(R.id.subject_row_name)
        val textViewSubjectDay: TextView = view.findViewById<TextView>(R.id.subject_row_day)
        val textViewSubjectHours: TextView = view.findViewById<TextView>(R.id.subject_row_hours)
        val buttonInfo: Button = view.findViewById<Button>(R.id.subject_row_info)

        fun navigateToSubjectInfo(subjectId: Long) {
            val bundle = bundleOf(Pair("subjectId", subjectId))

            view.findNavController()
                .navigate(R.id.action_subjectsFragment_to_subjectInfoFragment, bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_row, parent, false)
        return SubjectsListHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectsListHolder, position: Int) {
        val subject = subjects.value?.get(position)

        holder.textViewSubjectName.text = subject?.name
        holder.textViewSubjectDay.text = subject?.day
        holder.textViewSubjectHours.text = subject?.getBeautifyRangeOfHours()

        holder.buttonInfo.setOnClickListener {
            holder.navigateToSubjectInfo(subject?.subjectId ?: 0)
        }
    }

    override fun getItemCount(): Int = subjects.value?.size ?: 0
}