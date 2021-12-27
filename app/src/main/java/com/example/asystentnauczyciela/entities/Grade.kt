package com.example.asystentnauczyciela.entities

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "grade")
    var grade: Float,

    @ColumnInfo(name = "weight")
    var weight: Int,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "subjectId", index = true)
    var subjectId: Long,

    @ColumnInfo(name = "albumNumber", index = true)
    var albumNumber: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gradeId")
    var gradeId: Long = 0L

    fun getGradeStr(): String = "Ocena: $grade"
    fun getWeightStr(): String = "Waga: $weight"
}