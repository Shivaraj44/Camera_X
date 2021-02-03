package com.example.camerax_gallery.OnetoMany

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity (tableName = "Folder")
data class Folder(

    @PrimaryKey
    @ColumnInfo(name = "FolderId")
     val FolderId: Int=0,

    @ColumnInfo(name = "albumName")
     val albumName: String




)


