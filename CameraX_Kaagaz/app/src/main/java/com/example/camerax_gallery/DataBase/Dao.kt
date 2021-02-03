package com.example.camerax_gallery.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface Dao {

@Insert
fun InserTodatabase(model:Model)


@Query("select * from Gallery")
fun getImages():List<Model>
}