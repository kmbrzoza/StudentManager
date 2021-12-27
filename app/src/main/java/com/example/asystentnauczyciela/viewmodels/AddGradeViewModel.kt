package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.daos.GradesDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import com.example.asystentnauczyciela.entities.Grade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGradeViewModel(
    application: Application,
    val subjectId: Long,
    val albumNumber: Long
) : ViewModel() {
    private val gradesDAO: GradesDAO = ManagerDatabase.getInstance(application).gradesDAO

    fun addGrade(grade: Grade) {
        viewModelScope.launch(Dispatchers.IO) {
            gradesDAO.addGrade(grade)
        }
    }
}