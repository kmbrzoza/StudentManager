package com.example.asystentnauczyciela.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "albumNumber")
    var albumNumber: Long = 0L
}