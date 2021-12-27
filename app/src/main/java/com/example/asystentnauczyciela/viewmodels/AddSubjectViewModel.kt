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

    fun checkSubjectTime(startHour: Int, startMin: Int, endHour: Int, endMin: Int): Boolean {
        if (startHour > endHour) return false
        if (startHour == endHour && startMin > endMin) return false
        if (startHour < 0 || startHour > 24) return false
        if (endHour < 0 || endHour > 24) return false
        if (startMin < 0 || startMin > 59) return false
        if (endMin < 0 || endMin > 59) return false
        return true
    }
}