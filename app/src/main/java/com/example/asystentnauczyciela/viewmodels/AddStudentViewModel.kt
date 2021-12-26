package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.daos.StudentsDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import com.example.asystentnauczyciela.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AddStudentViewModel(application: Application) : AndroidViewModel(application) {
    private val studentsDAO: StudentsDAO = ManagerDatabase.getInstance(application).studentsDAO

    fun addStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            studentsDAO.addStudent(student)
        }
    }
}