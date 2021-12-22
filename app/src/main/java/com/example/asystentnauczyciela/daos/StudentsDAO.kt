package com.example.asystentnauczyciela.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.asystentnauczyciela.entities.Student

@Dao
interface StudentsDAO {
    @Insert
    fun addStudent(student: Student)

    @Delete
    fun removeStudent(student: Student)

    @Query("SELECT * FROM Students")
    fun getAllStudents(): LiveData<List<Student>>
}