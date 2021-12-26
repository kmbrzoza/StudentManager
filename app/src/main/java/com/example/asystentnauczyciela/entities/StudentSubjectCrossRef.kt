package com.example.asystentnauczyciela.entities

import androidx.room.Entity

@Entity(tableName = "StudentSubject", primaryKeys = ["subjectId", "albumNumber"])
data class StudentSubjectCrossRef(
    val subjectId: Long,
    val albumNumber: Long
)