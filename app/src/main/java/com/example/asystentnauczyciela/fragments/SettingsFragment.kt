package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.factories.SettingsViewModelFactory
import com.example.asystentnauczyciela.helpers.OwnDialog
import com.example.asystentnauczyciela.viewmodels.SettingsViewModel

class SettingsFragment : Fragment() {
    lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = SettingsViewModelFactory((requireNotNull(this.activity).application))
        viewModel = ViewModelProvider(this, factory).get(SettingsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_settings_remove_students).apply {
            setOnClickListener {
                val dialog = OwnDialog(resources.getString(R.string.msg_remove_students)) {
                    viewModel.removeStudents()
                    Toast.makeText(activity, "Pomyślnie usunięto studentów", Toast.LENGTH_LONG)
                        .show()
                }
                dialog.show(childFragmentManager, null)
            }
        }

        view.findViewById<Button>(R.id.button_settings_remove_subjects).apply {
            setOnClickListener {
                val dialog = OwnDialog(getResources().getString(R.string.msg_remove_subjects)) {
                    viewModel.removeSubjects()
                    Toast.makeText(activity, "Pomyślnie usunięto przedmioty", Toast.LENGTH_LONG)
                        .show()
                }
                dialog.show(childFragmentManager, null)
            }
        }

        view.findViewById<Button>(R.id.button_settings_remove_all).apply {
            setOnClickListener {
                val dialog = OwnDialog(
                    getResources().getString(R.string.msg_remove_students_and_subjects)
                ) {
                    viewModel.removeAll()
                    Toast.makeText(
                        activity, "Pomyślnie usunięto studentów i przedmioty",
                        Toast.LENGTH_LONG
                    ).show()
                }
                dialog.show(childFragmentManager, null)
            }
        }

        view.findViewById<Button>(R.id.button_settings_back).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
            }
        }
    }
}