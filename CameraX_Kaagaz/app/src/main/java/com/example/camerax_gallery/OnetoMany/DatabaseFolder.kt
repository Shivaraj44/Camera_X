package com.example.camerax_gallery.OnetoMany

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.camerax_gallery.DataBase.DataBase


@Database(entities = [Album::class], version = 1,exportSchema = false)

abstract class DatabaseFolder:RoomDatabase() {
    abstract val FoolderDao: FoolderDao

    companion object{
        private var INSTANCE: DatabaseFolder?=null
        fun getInstance(context: Context): DatabaseFolder {
            var instance=INSTANCE
            if(instance==null){
                instance= Room.databaseBuilder(context, DatabaseFolder::class.java,"Gallery").build()
                INSTANCE=instance
            }
            return instance
        }
    }

}