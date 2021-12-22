package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R

class AddStudentFragment : Fragment() {
    //vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //vm init
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
                //TODO
            }
        }
    }
}