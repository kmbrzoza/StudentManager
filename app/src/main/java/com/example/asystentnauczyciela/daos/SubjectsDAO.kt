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

    @Query("DELETE FROM Subject")
    fun removeSubjects()

    @Query("SELECT * FROM Subject")
    fun getAllSubjects(): LiveData<List<Subject>>

    @Query("SELECT * FROM Subject WHERE SubjectId = :subjectId LIMIT 1")
    fun getSubject(subjectId: Long): LiveData<Subject>

    @Query("SELECT * FROM Student as s INNER JOIN SubjectStudent as ss ON s.albumNumber = ss.albumNumber WHERE ss.subjectId = :subjectId")
    fun getStudentsForSubject(subjectId: Long): LiveData<List<Student>>

    @Transaction
    @Query("SELECT * FROM Subject WHERE subjectId = :subjectId LIMIT 1")
    fun getSubjectWithStudents(subjectId: Long): LiveData<SubjectWithStudents>

    @Query("DELETE FROM SubjectStudent")
    fun removeSubjectStudent()
}