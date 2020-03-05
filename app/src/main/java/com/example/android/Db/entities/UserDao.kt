package com.example.android.Db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User):Long

    @Query("SELECT * FROM user where uid = $CURRENT_USER_ID")
    fun getUser():LiveData<User>
}