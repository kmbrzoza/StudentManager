package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.adapters.AssignStudentsToSubjectListAdapter
import com.example.asystentnauczyciela.factories.AssignStudentsToSubjectViewModelFactory
import com.example.asystentnauczyciela.viewmodels.AssignStudentsToSubjectViewModel

class AssignStudentsToSubjectFragment : Fragment() {
    lateinit var viewModel: AssignStudentsToSubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val subjectId = arguments?.getLong("subjectId")
        if (subjectId == null) {
            Toast.makeText(activity, "Niepoprawny przedmiot", Toast.LENGTH_LONG).show()
            backToSubjectInfo(subjectId)
            return null
        }

        val factory = AssignStudentsToSubjectViewModelFactory(
            (requireNotNull(this.activity).application),
            subjectId
        )
        viewModel = ViewModelProvider(this, factory)
            .get(AssignStudentsToSubjectViewModel::class.java)

        return inflater.inflate(R.layout.fragment_assign_students_to_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val assignStudentsToSubjectListAdapter =
            AssignStudentsToSubjectListAdapter(viewModel.students, viewModel)
        viewModel.students.observe(viewLifecycleOwner, {
            assignStudentsToSubjectListAdapter.notifyDataSetChanged()
        })

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.assign_student_to_subject_list).let {
            it.adapter = assignStudentsToSubjectListAdapter
            it.layoutManager = layoutManager
        }

        view.findViewById<Button>(R.id.button_assign_students_to_subject_back).apply {
            setOnClickListener {
                backToSubjectInfo(viewModel.subjectId)
            }
        }
    }

    private fun backToSubjectInfo(subjectId: Long?) {
        val bundle = bundleOf(Pair("subjectId", subjectId))

        view?.findNavController()
            ?.navigate(R.id.action_assignStudentToSubjectFragment_to_subjectInfoFragment, bundle)
    }
}