package com.example.android.Db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student_info(
    @PrimaryKey(autoGenerate = true)
    var s_id : Int?=0,
    var fname : String?=null,
    var lname : String?=null,
    var dept_id : Int?=0,
    var pcourse_id : Int?=null
)
{

}