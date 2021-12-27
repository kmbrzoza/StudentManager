package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.adapters.SubjectsListAdapter
import com.example.asystentnauczyciela.viewmodels.SubjectsViewModel

class SubjectsFragment : Fragment() {
    lateinit var viewModel: SubjectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(SubjectsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_subjects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectsListAdapter = SubjectsListAdapter(viewModel.subjects)
        viewModel.subjects.observe(viewLifecycleOwner, {
            subjectsListAdapter.notifyDataSetChanged()
        })

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.subjects_list).let {
            it.adapter = subjectsListAdapter
            it.layoutManager = layoutManager
        }

        view.findViewById<Button>(R.id.button_subjects_back).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_subjectsFragment_to_mainFragment)
            }
        }
    }
}