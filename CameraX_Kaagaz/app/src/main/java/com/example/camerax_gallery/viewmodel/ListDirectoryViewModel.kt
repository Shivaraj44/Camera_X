package com.example.camerax_gallery.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.camerax_gallery.OnetoMany.Album
import com.example.camerax_gallery.reposotery.Repository

class ListDirectoryViewModel : ViewModel() {

    val reposotory = Repository()
    fun fetchAllAlbums(context: Context) : LiveData<List<Album>>{
        return reposotory.fetchAlbumsFromDatabase(context)
    }
}