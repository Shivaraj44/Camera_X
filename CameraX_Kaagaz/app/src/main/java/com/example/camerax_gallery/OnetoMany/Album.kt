package com.example.camerax_gallery.OnetoMany

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AlbumTable")
data class Album(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "FolderId")
    var FolderId: Int=1,

    @ColumnInfo(name = "albumName")
    val albumName: String,

    @ColumnInfo(name  ="timeStamp")
    val timeStamp : Long
)