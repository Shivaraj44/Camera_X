package com.example.camerax_gallery.OnetoMany

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoolderDao {

        @Insert
    fun insertname(album: Album)

    @Query("Select * from albumtable")
    fun fetchAlbumNames() : LiveData<List<Album>>


}