package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.daos.StudentsDAO
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import com.example.asystentnauczyciela.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssignStudentsToSubjectViewModel(application: Application, val subjectId: Long) :
    ViewModel() {
    private val studentsDAO: StudentsDAO = ManagerDatabase.getInstance(application).studentsDAO

    val students: LiveData<List<Student>> =
        studentsDAO.getStudentsThatDontBelongToSubject(subjectId)

    fun assignStudentToSubject(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            studentsDAO.assignStudentToSubject(student.albumNumber, subjectId)
        }
    }
}