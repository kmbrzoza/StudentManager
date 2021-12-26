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

    @Query("SELECT * FROM students as s WHERE NOT EXISTS(SELECT 1 FROM StudentSubject as sub WHERE sub.subjectId=(:subjectId) AND sub.albumNumber = s.albumNumber)")
    fun getStudentsThatDontBelongToSubject(subjectId: Long): LiveData<List<Student>>

    @Query("INSERT INTO StudentSubject (albumNumber, subjectId) VALUES (:albumNumber, :subjectId)")
    fun assignStudentToSubject(albumNumber: Long, subjectId: Long)
}