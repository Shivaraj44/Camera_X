package com.example.camerax_gallery.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Gallery")
data class Model (


    @PrimaryKey (autoGenerate = true)
    val Id:Int=0,

@ColumnInfo(name = "PathId")
    val uri:String
)