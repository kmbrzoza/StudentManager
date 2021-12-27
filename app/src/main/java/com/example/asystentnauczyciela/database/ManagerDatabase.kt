package com.example.asystentnauczyciela.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asystentnauczyciela.daos.GradesDAO
import com.example.asystentnauczyciela.daos.StudentsDAO
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.entities.Grade
import com.example.asystentnauczyciela.entities.Student
import com.example.asystentnauczyciela.entities.SubjectStudent
import com.example.asystentnauczyciela.entities.Subject

@Database(
    entities = [Student::class, Subject::class, SubjectStudent::class, Grade::class],
    version = 23,
    exportSchema = false
)
abstract class ManagerDatabase : RoomDatabase() {
    abstract val studentsDAO: StudentsDAO
    abstract val subjectsDAO: SubjectsDAO
    abstract val gradesDAO: GradesDAO

    companion object {

        @Volatile
        private var INSTANCE: ManagerDatabase? = null

        fun getInstance(context: Context): ManagerDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ManagerDatabase::class.java,
                        "ManagerDatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}