package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R

class AddSubjectFragment : Fragment() {
    //vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //vm init

        return inflater.inflate(R.layout.fragment_add_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_add_subject_back).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_addSubjectFragment_to_mainFragment)
            }
        }

        view.findViewById<Button>(R.id.button_add_subject).apply {
            setOnClickListener {
                //TODO
            }
        }
    }
}