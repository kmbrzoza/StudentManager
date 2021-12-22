package com.example.asystentnauczyciela.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AlbumNumber")
    val albumNumber: Long,

    @ColumnInfo(name = "FirstName")
    var firstName: String,

    @ColumnInfo(name = "LastName")
    var lastName: String
)