package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.adapters.SubjectStudentGradesListAdapter
import com.example.asystentnauczyciela.entities.Grade
import com.example.asystentnauczyciela.entities.Student
import com.example.asystentnauczyciela.factories.SubjectStudentGradesViewModelFactory
import com.example.asystentnauczyciela.viewmodels.SubjectStudentGradesViewModel

class SubjectStudentGradesFragment : Fragment() {
    lateinit var viewModel: SubjectStudentGradesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val subjectId = arguments?.getLong("subjectId")
        val albumNumber = arguments?.getLong("albumNumber")

        if (subjectId == null || albumNumber == null) {
            Toast.makeText(activity, "Niepoprawny przedmiot lub student", Toast.LENGTH_LONG).show()
            backToSubjectInfo(subjectId)
            return null
        }

        val factory = SubjectStudentGradesViewModelFactory(
            (requireNotNull(this.activity).application),
            subjectId,
            albumNumber
        )
        viewModel = ViewModelProvider(this, factory)
            .get(SubjectStudentGradesViewModel::class.java)

        return inflater.inflate(R.layout.fragment_subject_student_grades, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectStudentGradesListAdapter =
            SubjectStudentGradesListAdapter(viewModel.grades)
        viewModel.grades.observe(viewLifecycleOwner, {
            subjectStudentGradesListAdapter.notifyDataSetChanged()
            setGradesAverageInfoInView(view, viewModel.getGradesAverage(it))
        })
        viewModel.student.observe(viewLifecycleOwner, {
            setStudentInfoInView(view, it)
        })

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.subject_student_grades_list).let {
            it.adapter = subjectStudentGradesListAdapter
            it.layoutManager = layoutManager
        }

        view.findViewById<Button>(R.id.button_subject_student_grades_back).apply {
            setOnClickListener {
                backToSubjectInfo(viewModel.subjectId)
            }
        }

        view.findViewById<Button>(R.id.button_subject_student_grades_new_grade).apply {
            setOnClickListener {
                val bundle = bundleOf(
                    Pair("subjectId", viewModel.subjectId),
                    Pair("albumNumber", viewModel.albumNumber)
                )

                view.findNavController()
                    .navigate(
                        R.id.action_subjectStudentGradesFragment_to_addGradeFragment,
                        bundle
                    )
            }
        }
    }

    private fun backToSubjectInfo(subjectId: Long?) {
        val bundle = bundleOf(Pair("subjectId", subjectId))

        view?.findNavController()
            ?.navigate(R.id.action_subjectStudentGradesFragment_to_subjectInfoFragment, bundle)
    }

    private fun setStudentInfoInView(
        view: View,
        student: Student?
    ) {
        view.findViewById<TextView>(R.id.subject_student_grades_first_name).text =
            student?.firstName
        view.findViewById<TextView>(R.id.subject_student_grades_last_name).text =
            student?.lastName
        view.findViewById<TextView>(R.id.subject_student_grades_album_number).text =
            "${getResources().getString(R.string.album_number)}: ${student?.albumNumber?.toString()}"
    }

    private fun setGradesAverageInfoInView(view: View, gradesAverage: Float) {
        view.findViewById<TextView>(R.id.subject_student_grades_grade_average).text =
            "${getResources().getString(R.string.grade_average)} ${gradesAverage.toString()}"
    }
}