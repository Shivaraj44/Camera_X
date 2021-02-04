package com.example.camerax_gallery.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.camerax_gallery.OnetoMany.Album
import com.example.camerax_gallery.R
import com.example.camerax_gallery.adapter.ListDirectoryAdapter
import com.example.camerax_gallery.listner.ItemClickListener
import com.example.camerax_gallery.viewmodel.UserViewModel
import com.example.camerax_gallery.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_list_photos.*
/**
fetching  all the album names from database and displaying into a recyclerview with a gridLayout format
*/
class ListDirectoryActivity : AppCompatActivity(), ItemClickListener {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_photos)
        viewModel = UserViewModelFactory(this).create(UserViewModel::class.java)
//fetch from viewmodel class with the help of live data
        viewModel.fetchAllAlbums().observe(this, Observer {
            it?.let {
                setRecyclerAdapter(it)
            }
        })
    }

    private fun setRecyclerAdapter(it: List<Album>) {
        val gridLayoutManager = GridLayoutManager(this, 4)
        val listPhotosAdapter = ListDirectoryAdapter(it, this)
        listRecyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = listPhotosAdapter
        }
    }


    //listener used to active when a item clicked on the recyclerview
    //passing the album name to another activity
    override fun onItemClicked(name: String) {

        val album_name = name
        Log.d("nameshiv", "" + album_name)
        val intent = Intent(this, DisplayGalleryActivity::class.java)
        intent.putExtra("data", album_name)
        startActivity(intent)
    }
}