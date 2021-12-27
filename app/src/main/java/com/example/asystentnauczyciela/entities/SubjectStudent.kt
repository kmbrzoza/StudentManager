package com.example.asystentnauczyciela.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "SubjectStudent", foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = arrayOf("subjectId"),
            childColumns = arrayOf("subjectId")
        ),
        ForeignKey(
            entity = Student::class,
            parentColumns = arrayOf("albumNumber"),
            childColumns = arrayOf("albumNumber")
        )
    ]
)
data class SubjectStudent(
    @PrimaryKey(autoGenerate = true)
    val subjectStudentId: Long = 0L,

    @ColumnInfo(index = true)
    val subjectId: Long,
    @ColumnInfo(index = true)
    val albumNumber: Long
)