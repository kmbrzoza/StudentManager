package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import com.example.asystentnauczyciela.entities.SubjectWithStudents

class SubjectInfoViewModel(application: Application, val subjectId: Long) :
    ViewModel() {
    private val subjectsDAO: SubjectsDAO = ManagerDatabase.getInstance(application).subjectsDAO
    var subjectWithStudents: LiveData<SubjectWithStudents> =
        subjectsDAO.getSubjectWithStudents(subjectId)
}