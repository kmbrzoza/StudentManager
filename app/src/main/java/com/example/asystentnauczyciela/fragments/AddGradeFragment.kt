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
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.entities.Grade
import com.example.asystentnauczyciela.entities.Student
import com.example.asystentnauczyciela.factories.AddGradeViewModelFactory
import com.example.asystentnauczyciela.viewmodels.AddGradeViewModel

class AddGradeFragment : Fragment() {
    lateinit var viewModel: AddGradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val subjectId = arguments?.getLong("subjectId")
        val albumNumber = arguments?.getLong("albumNumber")

        if (subjectId == null || albumNumber == null) {
            Toast.makeText(activity, "Niepoprawny przedmiot lub student", Toast.LENGTH_LONG).show()
            backToSubjectStudentGrades(subjectId, albumNumber)
            return null
        }

        val factory = AddGradeViewModelFactory(
            (requireNotNull(this.activity).application),
            subjectId,
            albumNumber,
        )
        viewModel = ViewModelProvider(this, factory)
            .get(AddGradeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_add_grade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_add_grade_student_back).apply {
            setOnClickListener {
                backToSubjectStudentGrades(viewModel.subjectId, viewModel.albumNumber)
            }
        }

        view.findViewById<Button>(R.id.button_add_grade_student).apply {
            setOnClickListener {
                val gradeVal = view.findViewById<EditText>(R.id.spinner_add_grade_student_grade)
                    .text.trim().toString().toFloatOrNull()
                val weight = view.findViewById<EditText>(R.id.spinner_add_grade_student_weight)
                    .text.trim().toString().toIntOrNull()

                if (gradeVal == null || weight == null) {
                    Toast.makeText(activity, "Niepoprawny ocena lub waga", Toast.LENGTH_LONG).show()
                } else {
                    val description =
                        view.findViewById<EditText>(R.id.spinner_add_grade_student_description)
                            .text.trim().toString()
                    val grade = Grade(
                        gradeVal,
                        weight,
                        description,
                        viewModel.subjectId,
                        viewModel.albumNumber
                    )
                    viewModel.addGrade(grade)
                    Toast.makeText(activity, "Pomyslnie dodano ocenę", Toast.LENGTH_LONG)
                        .show()
                    clearForm(view)
                }
            }
        }
    }

    private fun backToSubjectStudentGrades(subjectId: Long?, albumNumber: Long?) {
        val bundle = bundleOf(Pair("subjectId", subjectId), Pair("albumNumber", albumNumber))

        view?.findNavController()
            ?.navigate(R.id.action_addGradeFragment_to_subjectStudentGradesFragment, bundle)
    }

    private fun clearForm(view: View) {
        view.findViewById<EditText>(R.id.spinner_add_grade_student_grade).setText("")
        view.findViewById<EditText>(R.id.spinner_add_grade_student_weight).setText("")
        view.findViewById<EditText>(R.id.spinner_add_grade_student_description).setText("")
    }
}