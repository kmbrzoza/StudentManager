package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import com.example.asystentnauczyciela.entities.Subject

class SubjectsViewModel(application: Application) : AndroidViewModel(application) {
    private val subjectsDAO: SubjectsDAO = ManagerDatabase.getInstance(application).subjectsDAO
    val subjects: LiveData<List<Subject>> = subjectsDAO.getAllSubjects()
}