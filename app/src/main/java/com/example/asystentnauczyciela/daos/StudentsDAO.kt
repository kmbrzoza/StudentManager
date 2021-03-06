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

    @Query("DELETE FROM Student")
    fun removeStudents()

    @Query("SELECT * FROM Student WHERE albumNumber = :albumNumber")
    fun getStudent(albumNumber: Long): LiveData<Student>

    @Query("SELECT * FROM Student as s WHERE NOT EXISTS(SELECT 1 FROM SubjectStudent as sub WHERE sub.subjectId = :subjectId AND sub.albumNumber = s.albumNumber)")
    fun getStudentsThatDontBelongToSubject(subjectId: Long): LiveData<List<Student>>

    @Query("INSERT INTO SubjectStudent (albumNumber, subjectId) VALUES (:albumNumber, :subjectId)")
    fun assignStudentToSubject(albumNumber: Long, subjectId: Long)
}