package com.example.asystentnauczyciela.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.viewmodels.SubjectStudentGradesViewModel

class SubjectStudentGradesViewModelFactory(
    private val application: Application,
    private val subjectId: Long,
    private val albumNumber: Long,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectStudentGradesViewModel::class.java)) {
            return SubjectStudentGradesViewModel(application, subjectId, albumNumber) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}