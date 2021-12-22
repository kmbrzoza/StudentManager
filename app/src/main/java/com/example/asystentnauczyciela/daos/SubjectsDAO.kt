package com.example.asystentnauczyciela.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.asystentnauczyciela.entities.Subject

@Dao
interface SubjectsDAO {
    @Insert
    fun addSubject(subject: Subject)

    @Delete
    fun removeSubject(subject: Subject)

    @Query("SELECT * FROM Subjects")
    fun getAllStudents(): LiveData<List<Subject>>
}