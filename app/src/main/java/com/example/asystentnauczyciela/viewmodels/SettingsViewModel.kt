package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.daos.GradesDAO
import com.example.asystentnauczyciela.daos.StudentsDAO
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : ViewModel() {
    private val gradesDAO: GradesDAO = ManagerDatabase.getInstance(application).gradesDAO
    private val subjectsDAO: SubjectsDAO = ManagerDatabase.getInstance(application).subjectsDAO
    private val studentsDAO: StudentsDAO = ManagerDatabase.getInstance(application).studentsDAO

    fun removeStudents() {
        viewModelScope.launch(Dispatchers.IO) {
            gradesDAO.removeGrades()
            subjectsDAO.removeSubjectStudent()
            studentsDAO.removeStudents()
        }
    }

    fun removeSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            gradesDAO.removeGrades()
            subjectsDAO.removeSubjectStudent()
            subjectsDAO.removeSubjects()
        }
    }

    fun removeAll() {
        viewModelScope.launch(Dispatchers.IO) {
            gradesDAO.removeGrades()
            subjectsDAO.removeSubjectStudent()
            studentsDAO.removeStudents()
            subjectsDAO.removeSubjects()
        }
    }
}