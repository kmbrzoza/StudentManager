package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.entities.Subject
import com.example.asystentnauczyciela.viewmodels.AddSubjectViewModel

class AddSubjectFragment : Fragment() {
    lateinit var viewModel: AddSubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(AddSubjectViewModel::class.java)

        return inflater.inflate(R.layout.fragment_add_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerDay: Spinner = view.findViewById<Spinner>(R.id.add_subject_day)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.weekdays,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDay.adapter = adapter
        }

        view.findViewById<Button>(R.id.button_add_subject_back).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_addSubjectFragment_to_mainFragment)
            }
        }

        view.findViewById<Button>(R.id.button_add_subject).apply {
            setOnClickListener {
                val name = view.findViewById<EditText>(R.id.add_subject_name)
                    .text.trim().toString()
                val day = spinnerDay.selectedItem.toString()
                val startHour = view.findViewById<EditText>(R.id.add_subject_start_hour)
                    .text.trim().toString().toIntOrNull()
                val startMin = view.findViewById<EditText>(R.id.add_subject_start_min)
                    .text.trim().toString().toIntOrNull()
                val endHour = view.findViewById<EditText>(R.id.add_subject_end_hour)
                    .text.trim().toString().toIntOrNull()
                val endMin = view.findViewById<EditText>(R.id.add_subject_end_min)
                    .text.trim().toString().toIntOrNull()

                if (TextUtils.isEmpty(name) ||
                    startHour == null || startMin == null ||
                    endHour == null || endMin == null
                ) {
                    Toast.makeText(activity, "Nieprawidłowe dane przedmiotu", Toast.LENGTH_LONG)
                        .show()
                } else {
                    if (!viewModel.checkSubjectTime(startHour, startMin, endHour, endMin)) {
                        Toast.makeText(activity, "Nieprawidłowe godziny", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        val subject = Subject(name, day, startHour, startMin, endHour, endMin)
                        viewModel.addSubject(subject)
                        clearForm(view)
                        Toast.makeText(activity, "Pomyslnie dodano przedmiot", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    private fun clearForm(view: View) {
        view.findViewById<EditText>(R.id.add_subject_name).setText("")
        view.findViewById<EditText>(R.id.add_subject_start_hour).setText("")
        view.findViewById<EditText>(R.id.add_subject_start_min).setText("")
        view.findViewById<EditText>(R.id.add_subject_end_hour).setText("")
        view.findViewById<EditText>(R.id.add_subject_end_min).setText("")
        view.findViewById<Spinner>(R.id.add_subject_day).setSelection(0)
    }
}