package com.example.asystentnauczyciela.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.viewmodels.SubjectInfoViewModel

class SubjectInfoViewModelFactory(
    private val application: Application,
    private val subjectId: Long
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectInfoViewModel::class.java)) {
            return SubjectInfoViewModel(application, subjectId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}