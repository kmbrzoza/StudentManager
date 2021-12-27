package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.asystentnauczyciela.daos.GradesDAO
import com.example.asystentnauczyciela.daos.StudentsDAO
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import com.example.asystentnauczyciela.entities.Grade
import com.example.asystentnauczyciela.entities.Student
import com.example.asystentnauczyciela.entities.Subject

class SubjectStudentGradesViewModel(
    application: Application,
    val subjectId: Long,
    val albumNumber: Long,
) : ViewModel() {
    private val gradesDAO: GradesDAO = ManagerDatabase.getInstance(application).gradesDAO
    private val subjectsDAO: SubjectsDAO = ManagerDatabase.getInstance(application).subjectsDAO
    private val studentsDAO: StudentsDAO = ManagerDatabase.getInstance(application).studentsDAO

    val grades: LiveData<List<Grade>> = gradesDAO.getGradesForSubjectStudent(subjectId, albumNumber)

    val student: LiveData<Student> = studentsDAO.getStudent(albumNumber)

    val subject: LiveData<Subject> = subjectsDAO.getSubject(subjectId)

    fun getGradesAverage(gradeList: List<Grade>): Float {
        var result = 0F
        if (!gradeList.any())
            return result

        var multiplied = 0F
        var weights = 0

        gradeList.forEach {
            multiplied += it.grade * it.weight
            weights += it.weight
        }

        result = multiplied / weights

        return Math.round(result * 100F) / 100F
    }
}