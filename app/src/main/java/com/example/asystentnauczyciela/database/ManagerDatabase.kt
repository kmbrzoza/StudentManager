package com.example.asystentnauczyciela.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asystentnauczyciela.daos.StudentsDAO
import com.example.asystentnauczyciela.daos.SubjectsDAO
import com.example.asystentnauczyciela.entities.Student

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class ManagerDatabase : RoomDatabase() {
    abstract val studentsDAO: StudentsDAO
    abstract val subjectsDAO: SubjectsDAO

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