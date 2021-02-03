package com.example.camerax_gallery.OnetoMany

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "Images")
data class Images (
    @PrimaryKey
    @ColumnInfo(name = "ImageId")
     val ImageId: Int,

    @ColumnInfo(name = "ImageName")
     val ImageName: String,

    @ColumnInfo(name = "ImageCommon_Id")
     val ImageCommon_Id: Int

)