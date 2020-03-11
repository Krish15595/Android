package com.example.android.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.Db.entities.*

@Database(
    entities = [User::class,Dept_info::class,Course_info::class,Student_info::class],
    version = 1
)
abstract class AppDatabase :RoomDatabase(){
    abstract fun getUserDao() :UserDao
    abstract fun getDeptDao() :DeptDao
    abstract fun getCourseDao() :CourseDao
    abstract fun getStudentDao() :StudentDao

    var Database_Name="MyDatabase.db"
    companion object{
        @Volatile
        private var instance:AppDatabase?=null
        private var LOCK=Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance=it
            }
        }
        private fun buildDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabase.db"
        ).build()
    }
}