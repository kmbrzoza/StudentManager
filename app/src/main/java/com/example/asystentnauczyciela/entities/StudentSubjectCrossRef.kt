package com.example.asystentnauczyciela.entities

import androidx.room.Entity

@Entity(tableName = "StudentSubject", primaryKeys = ["albumNumber", "subjectId"])
data class StudentSubjectCrossRef(
    val albumNumber: Long,
    val subjectId: Long
)