package com.example.camerax_gallery.reposotery

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.camerax_gallery.DataBase.DataBase
import com.example.camerax_gallery.DataBase.Model
import com.example.camerax_gallery.OnetoMany.Album
import com.example.camerax_gallery.OnetoMany.DatabaseFolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Reposotory {


    fun insertDataToDatabase(id: Int, uri: String, context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            val model =
                Model(id, uri)
            DataBase.getInstance(context).Dao.InserTodatabase(model)
        }
    }

    fun insertgalleryname(id: Int, name: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {

            val album = Album(id, albumName = name, timeStamp = System.currentTimeMillis())
            DatabaseFolder.getInstance(context).FoolderDao.insertname(album)
        }
    }

    fun fetchAlbumsFromDatabase(context: Context):LiveData<List<Album>> {

        return DatabaseFolder.getInstance(context).FoolderDao.fetchAlbumNames()
    }
}