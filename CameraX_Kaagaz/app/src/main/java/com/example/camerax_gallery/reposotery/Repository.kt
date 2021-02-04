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
/**
Model class where it will be having access to Database and networking operations
 Repository  communicates with local and remote data source
 */
class Repository {



//Storing the album name to database and passing to viewmodel
    fun insertAlbumName(id: Int, name: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {

            val album = Album(id, albumName = name, timeStamp = System.currentTimeMillis())
            DatabaseFolder.getInstance(context).FoolderDao.insertname(album)
        }
    }
//fetching all the album names from database and passing to viewmodel
    fun fetchAlbumsFromDatabase(context: Context):LiveData<List<Album>> {

        return DatabaseFolder.getInstance(context).FoolderDao.fetchAlbumNames()
    }
}