package com.example.asystentnauczyciela.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Long,

    @ColumnInfo(name = "Name")
    var name: String,

    @ColumnInfo(name = "Day")
    var day: String,

    @ColumnInfo(name = "StartHour")
    var startHour: String,

    @ColumnInfo(name = "EndHour")
    var endtHour: String
)