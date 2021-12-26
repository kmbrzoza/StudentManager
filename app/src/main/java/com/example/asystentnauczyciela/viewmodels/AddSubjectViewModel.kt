package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.database.ManagerDatabase
import com.example.asystentnauczyciela.entities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddSubjectViewModel(application: Application) : AndroidViewModel(application) {
    private val subjectsDAO: SubjectsDAO = ManagerDatabase.getInstance(application).subjectsDAO

    fun addSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectsDAO.addSubject(subject)
        }
    }
}