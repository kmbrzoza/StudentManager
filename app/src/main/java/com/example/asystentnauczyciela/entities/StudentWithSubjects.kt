package com.example.asystentnauczyciela.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class StudentWithSubjects(
    @Embedded
    val student: Student,

    @Relation(
        parentColumn = "albumNumber",
        entityColumn = "subjectId",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subject: List<Subject>
)