package com.example.camerax_gallery.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.example.camerax_gallery.OnetoMany.Album
import com.example.camerax_gallery.reposotery.Reposotory


class UserViewModel(private val context: Context):ViewModel() {
    private val repository = Reposotory()


    fun insertDataToDatabase(id:Int,uri: String) {
        repository.insertDataToDatabase(id, uri, context)
    }

    fun insertgalleryname(id:Int,name:String){
        repository.insertgalleryname(id,name,context)
    }

    fun fetchAllAlbums() : LiveData<List<Album>> {
        return repository.fetchAlbumsFromDatabase(context)
    }



}