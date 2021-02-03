package com.example.camerax_gallery.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.camerax_gallery.listner.ItemClickListener
import com.example.camerax_gallery.OnetoMany.Album
import com.example.camerax_gallery.R
import com.example.camerax_gallery.adapter.ListDirectoryAdapter
import com.example.camerax_gallery.viewmodel.UserViewModel
import com.example.camerax_gallery.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_list_photos.*

class ListDirectoryActivity : AppCompatActivity(), ItemClickListener {
    private lateinit var viewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_photos)
        viewModel = UserViewModelFactory(this).create(UserViewModel::class.java)

viewModel.fetchAllAlbums().observe(this, Observer {
    it?.let {
        setRecyclerAdapter(it)
    }
})
    }

    private fun setRecyclerAdapter(it: List<Album>) {
        val gridLayoutManager = GridLayoutManager(this,5)
        val listPhotosAdapter = ListDirectoryAdapter(it,this)
        listRecyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = listPhotosAdapter
        }
    }

    override fun onItemClicked(name: String) {
       // Open one more activity and pass album.albumName

        val item_name=name
        Log.d("nameshiv",""+item_name)
        val intent= Intent(this, DisplayGalleryActivity::class.java)
       intent.putExtra("data",item_name)
        startActivity(intent)
    }
}