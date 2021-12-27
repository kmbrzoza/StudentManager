//package com.example.asystentnauczyciela.entities
//
//import androidx.room.Embedded
//import androidx.room.Junction
//import androidx.room.Relation
//
//data class SubjectStudentWithGrades(
//    @Embedded
//    var subjectStudent: SubjectStudent,
//
//    @Relation(
//        parentColumn = "subjectStudentId",
//        entityColumn = "subjectStudentId",
//    )
//    var grades: List<Grade>
//) {
//    fun calculateGradesAverage(): Float {
//        if (grades.any { it.grade == null }) return 0F
//
//        var multiplied = 0F
//        var weights = 0
//
//        grades.forEach {
//            multiplied += it.grade!! * it.weight
//            weights += it.weight
//        }
//
//        return multiplied / weights
//    }
//}