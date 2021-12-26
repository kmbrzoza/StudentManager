package com.example.asystentnauczyciela.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.viewmodels.AssignStudentsToSubjectViewModel
import com.example.asystentnauczyciela.viewmodels.SubjectInfoViewModel

class AssignStudentsToSubjectViewModelFactory(
    private val application: Application,
    private val subjectId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssignStudentsToSubjectViewModel::class.java)) {
            return AssignStudentsToSubjectViewModel(application, subjectId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}