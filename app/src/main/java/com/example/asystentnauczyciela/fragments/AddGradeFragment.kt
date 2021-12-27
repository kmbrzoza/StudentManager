package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.entities.Grade
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
        viewModel = ViewModelProvider(this, factory).get(AddGradeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_add_grade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerGrade: Spinner = view.findViewById<Spinner>(R.id.spinner_add_grade_student_grade)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.grade_values,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGrade.adapter = adapter
        }

        val spinnerWeight: Spinner =
            view.findViewById<Spinner>(R.id.spinner_add_grade_student_weight)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.grade_weights,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerWeight.adapter = adapter
        }

        view.findViewById<Button>(R.id.button_add_grade_student).apply {
            setOnClickListener {
                val gradeVal = spinnerGrade.selectedItem.toString().toFloat()
                val weight = spinnerWeight.selectedItem.toString().toInt()
                val description =
                    view.findViewById<EditText>(R.id.add_grade_student_description).text.trim()
                        .toString()
                val grade = Grade(
                    gradeVal,
                    weight,
                    description,
                    viewModel.subjectId,
                    viewModel.albumNumber
                )
                viewModel.addGrade(grade)
                Toast.makeText(activity, "Pomyslnie dodano ocenę", Toast.LENGTH_LONG).show()
                clearForm(view)
            }
        }

        view.findViewById<Button>(R.id.button_add_grade_student_back).apply {
            setOnClickListener {
                backToSubjectStudentGrades(viewModel.subjectId, viewModel.albumNumber)
            }
        }
    }

    private fun backToSubjectStudentGrades(subjectId: Long?, albumNumber: Long?) {
        val bundle = bundleOf(Pair("subjectId", subjectId), Pair("albumNumber", albumNumber))

        view?.findNavController()
            ?.navigate(R.id.action_addGradeFragment_to_subjectStudentGradesFragment, bundle)
    }

    private fun clearForm(view: View) {
        view.findViewById<Spinner>(R.id.spinner_add_grade_student_grade).setSelection(0)
        view.findViewById<Spinner>(R.id.spinner_add_grade_student_weight).setSelection(0)
        view.findViewById<EditText>(R.id.add_grade_student_description).setText("")
    }
}