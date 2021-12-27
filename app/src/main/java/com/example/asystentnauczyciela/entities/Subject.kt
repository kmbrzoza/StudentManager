package com.example.asystentnauczyciela.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Subject")
data class Subject(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "day")
    var day: String,

    @ColumnInfo(name = "startHour")
    var startHour: Int,

    @ColumnInfo(name = "startMin")
    var startMin: Int,

    @ColumnInfo(name = "endHour")
    var endHour: Int,

    @ColumnInfo(name = "endMin")
    var endMin: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subjectId")
    var subjectId: Long = 0L

    fun getBeautifyStartHours(): String =
        "${String.format("%02d", startHour)}:${String.format("%02d", startMin)}"

    fun getBeautifyEndHours(): String =
        "${String.format("%02d", endHour)}:${String.format("%02d", endMin)}"

    fun getBeautifyRangeOfHours(): String = "${getBeautifyStartHours()} - ${getBeautifyEndHours()}"
}