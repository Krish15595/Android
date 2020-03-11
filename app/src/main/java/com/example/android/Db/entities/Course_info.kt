package com.example.android.Db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course_info(
    @PrimaryKey(autoGenerate = true)
    var c_id : Int?=0,
    var c_name : String?=null
)
{

}