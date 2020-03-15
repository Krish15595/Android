package com.example.android.Db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courseInfo: Course_info):Long

    @Query("SELECT * FROM course_info")
    fun getCourse(): LiveData<List<Course_info>>
}