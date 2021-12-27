package com.example.asystentnauczyciela.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.asystentnauczyciela.entities.Grade

@Dao
interface GradesDAO {
    @Insert
    fun addGrade(grade: Grade)

    @Query("DELETE FROM Grade")
    fun removeGrades()

    @Query("SELECT subjectStudentId FROM SubjectStudent WHERE subjectId = :subjectId AND albumNumber = :albumNumber")
    fun getSubjectStudentId(subjectId: Long, albumNumber: Long): LiveData<Long>

    @Transaction
    @Query("SELECT * FROM Grade WHERE subjectId = :subjectId AND albumNumber = :albumNumber")
    fun getGradesForSubjectStudent(subjectId: Long, albumNumber: Long): LiveData<List<Grade>>
}