package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.daos.StudentsDAO
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import com.example.asystentnauczyciela.entities.Student
import com.example.asystentnauczyciela.entities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AssignStudentsToSubjectViewModel(application: Application, val subjectId: Long) :
    AndroidViewModel(application) {
    private val studentsDAO: StudentsDAO = ManagerDatabase.getInstance(application).studentsDAO
    private val subjectsDAO: SubjectsDAO = ManagerDatabase.getInstance(application).subjectsDAO

    val students: LiveData<List<Student>> =
        studentsDAO.getStudentsThatDontBelongToSubject(subjectId)

    //val subject: LiveData<Subject> = subjectsDAO.getSubject(subjectId)
    fun assignStudentToSubject(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            studentsDAO.assignStudentToSubject(student.albumNumber, subjectId)
        }
    }
}