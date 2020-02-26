package com.example.android.Db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID=0
@Entity
data class User(
    var id : Int?=0,
    var name : String?=null,
    var email : String?=null,
    var created_at : String?=null,
    var updated_at : String?=null
)
{
    @PrimaryKey(autoGenerate = false)
    var uid:Int= CURRENT_USER_ID
}