package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.entities.Student
import com.example.asystentnauczyciela.viewmodels.AddStudentViewModel

class AddStudentFragment : Fragment() {
    lateinit var viewModel: AddStudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(AddStudentViewModel::class.java)

        return inflater.inflate(R.layout.fragment_add_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_add_student_back).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_addStudentFragment_to_mainFragment)
            }
        }

        view.findViewById<Button>(R.id.button_add_student).apply {
            setOnClickListener {
                val firstName =
                    view.findViewById<EditText>(R.id.add_student_first_name).text.trim().toString()
                val lastName =
                    view.findViewById<EditText>(R.id.add_student_last_name).text.trim().toString()

                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
                    Toast.makeText(activity, "Nieprawidłowe dane studenta", Toast.LENGTH_LONG)
                        .show()
                } else {
                    viewModel.addStudent(Student(firstName, lastName))
                    clearForm(view)
                    Toast.makeText(activity, "Pomyslnie dodano studenta", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun clearForm(view: View) {
        view.findViewById<EditText>(R.id.add_student_first_name).setText("")
        view.findViewById<EditText>(R.id.add_student_last_name).setText("")
    }
}