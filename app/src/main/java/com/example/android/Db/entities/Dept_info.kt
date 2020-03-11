package com.example.android.Db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dept_info(
    @PrimaryKey(autoGenerate = true)
    var d_id : Int?=0,
    var d_name : String?=null
)
{

}