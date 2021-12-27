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
    var startHour: String,

    @ColumnInfo(name = "startMin")
    var startMin: String,

    @ColumnInfo(name = "endHour")
    var endHour: String,

    @ColumnInfo(name = "endMin")
    var endMin: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subjectId")
    var subjectId: Long = 0L
}