package com.example.asystentnauczyciela.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SubjectWithStudents(
    @Embedded
    val subject: Subject,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "albumNumber",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)