package com.example.asystentnauczyciela.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
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
data class Grade(
    var grade: Float?,

    var weight: Int,

    var description: String,

    var subjectId: Long,

    var albumNumber: Long
) {
    @PrimaryKey(autoGenerate = true)
    var gradeId: Long = 0L

    fun getGradeStr(): String = "Ocena: $grade"
    fun getWeightStr(): String = "Waga: $weight"
}