package com.example.asystentnauczyciela.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystentnauczyciela.entities.Student
import com.example.asystentnauczyciela.entities.Subject
import com.example.asystentnauczyciela.entities.SubjectWithStudents
import kotlinx.coroutines.Deferred
import java.lang.Exception

@Dao
interface SubjectsDAO {
    @Insert
    fun addSubject(subject: Subject)

    @Delete
    fun removeSubject(subject: Subject)

    @Query("SELECT * FROM Subjects")
    fun getAllSubjects(): LiveData<List<Subject>>

    @Query("SELECT * FROM Subjects WHERE SubjectId = :subjectId LIMIT 1")
    fun getSubject(subjectId: Long): LiveData<Subject>

    @Query("SELECT * FROM students as s INNER JOIN StudentSubject as ss ON s.albumNumber = ss.albumNumber WHERE ss.subjectId = (:subjectId)")
    fun getStudentsForSubject(subjectId: Long): LiveData<List<Student>>

    @Transaction
    @Query("SELECT * FROM subjects WHERE subjectId = :subjectId LIMIT 1")
    fun getSubjectWithStudents(subjectId: Long): LiveData<SubjectWithStudents>
}