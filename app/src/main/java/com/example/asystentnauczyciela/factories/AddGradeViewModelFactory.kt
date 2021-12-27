package com.example.asystentnauczyciela.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.viewmodels.AddGradeViewModel

class AddGradeViewModelFactory(
    private val application: Application,
    private val subjectId: Long,
    private val albumNumber: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddGradeViewModel::class.java)) {
            return AddGradeViewModel(application, subjectId, albumNumber) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}