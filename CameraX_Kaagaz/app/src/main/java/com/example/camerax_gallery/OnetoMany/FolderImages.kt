package com.example.camerax_gallery.OnetoMany

import androidx.room.Embedded
import androidx.room.Relation

data class FolderImages  (@Embedded val folder: Folder,
                          @Relation(
    parentColumn = "FolderCommon_Id",
    entityColumn = "ImageCommon_Id"
)
                          val Imagelist:List<Images>
)