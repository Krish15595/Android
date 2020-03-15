package com.example.android.Db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeptDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dept: Dept_info):Long

    @Query("SELECT * FROM dept_info")
    fun getDept(): LiveData<List<Dept_info>>
}