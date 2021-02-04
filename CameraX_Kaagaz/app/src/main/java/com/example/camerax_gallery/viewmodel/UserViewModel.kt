package com.example.camerax_gallery.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.example.camerax_gallery.OnetoMany.Album
import com.example.camerax_gallery.reposotery.Repository

/**Viewmodel class bridge between view and Model class where viewmodel is lifecycler awar  component
helps to save the data on restarting
User logic part is written here

 passes the information to Model class where it has repository which will be having access to database operations

 */
class UserViewModel(private val context: Context):ViewModel() {
    private val repository = Repository()



// passing album name to repository class (Model)
    fun insertalbumname(id:Int, name:String){
        repository.insertAlbumName(id,name,context)
    }

    //getting the list of album name from Repository via live data
    fun fetchAllAlbums() : LiveData<List<Album>> {
        return repository.fetchAlbumsFromDatabase(context)
    }



}