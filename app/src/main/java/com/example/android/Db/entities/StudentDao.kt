package com.example.android.Db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courseInfo: Student_info):Long

    @Query("SELECT * FROM Student_info")
    fun getStudentDetails(): LiveData<List<Student_info>>
}