package com.example.camerax_gallery.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.camerax_gallery.OnetoMany.Folder


@Database(entities = [Model::class], version = 1,exportSchema = false)
abstract class DataBase: RoomDatabase() {
abstract val Dao:Dao

    companion object{
        private var INSTANCE:DataBase?=null
        fun getInstance(context: Context):DataBase{
            var instance=INSTANCE
            if(instance==null){
                instance= Room.databaseBuilder(context,DataBase::class.java,"Gallery").build()
                INSTANCE=instance
            }
            return instance
        }
    }
}