package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.adapters.SubjectStudentsListAdapter
import com.example.asystentnauczyciela.entities.Subject
import com.example.asystentnauczyciela.factories.SubjectInfoViewModelFactory
import com.example.asystentnauczyciela.viewmodels.SubjectInfoViewModel

class SubjectInfoFragment : Fragment() {
    lateinit var viewModel: SubjectInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val subjectId = arguments?.getLong("subjectId")
        if (subjectId == null) {
            Toast.makeText(activity, "Niepoprawny przedmiot", Toast.LENGTH_LONG).show()
            backToSubjects()
            return null
        }

        val factory =
            SubjectInfoViewModelFactory((requireNotNull(this.activity).application), subjectId)
        viewModel = ViewModelProvider(this, factory).get(SubjectInfoViewModel::class.java)

        return inflater.inflate(R.layout.fragment_subject_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectStudentsListAdapter = SubjectStudentsListAdapter(viewModel.subjectWithStudents)
        viewModel.subjectWithStudents.observe(viewLifecycleOwner, {
            subjectStudentsListAdapter.notifyDataSetChanged()
            setSubjectInView(view, it.subject)
        })

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.subject_students_list).let {
            it.adapter = subjectStudentsListAdapter
            it.layoutManager = layoutManager
        }

        view.findViewById<Button>(R.id.button_subject_info_back).apply {
            setOnClickListener {
                backToSubjects()
            }
        }

        view.findViewById<Button>(R.id.button_assign_student).apply {
            setOnClickListener {
                val bundle = bundleOf(Pair("subjectId", viewModel.subjectId))
                view.findNavController().navigate(
                    R.id.action_subjectInfoFragment_to_assignStudentToSubjectFragment,
                    bundle
                )
            }
        }
    }

    private fun backToSubjects() {
        view?.findNavController()?.navigate(R.id.action_subjectInfoFragment_to_subjectsFragment)
    }

    private fun setSubjectInView(view: View, subject: Subject?) {
        view.findViewById<TextView>(R.id.subject_info_name).setText(subject?.name)
        view.findViewById<TextView>(R.id.subject_info_day).setText(subject?.day)
        view.findViewById<TextView>(R.id.subject_info_range_of_hours)
            .setText(subject?.getBeautifyRangeOfHours())
    }
}