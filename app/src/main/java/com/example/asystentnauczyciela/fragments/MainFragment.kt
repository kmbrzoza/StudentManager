package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_new_student).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_mainFragment_to_addStudentFragment)
            }
        }

        view.findViewById<Button>(R.id.button_new_subject).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_mainFragment_to_addSubjectFragment)
            }
        }

        view.findViewById<Button>(R.id.button_subjects).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_mainFragment_to_subjectsFragment)
            }
        }

        view.findViewById<Button>(R.id.button_additional_settings).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }
        }
    }
}