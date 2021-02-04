package com.example.camerax_gallery.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.camerax_gallery.adapter.DisplayAdapter
import com.example.camerax_gallery.listner.DisplayListner
import com.example.camerax_gallery.R
import kotlinx.android.synthetic.main.activity_show.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.File

/**
Display the Images inside the folder which we have selected with the help of recyclerview
using gridLinearlayout
 */
class DisplayGalleryActivity : AppCompatActivity(), DisplayListner {

    // var listfiles = Array<String>() // list of file paths

    lateinit var listFile: Array<File>
    var listofiles = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        val name = intent.getStringExtra("data")

        if (name != null) {
            //method to get all the images inside the storage
            getFromSdcard(name)
        }
    }

// using externalMediaDirs we are able to get all the photos from the folder
    fun getFromSdcard(root: String) {
        CoroutineScope(IO).launch {
            val file = externalMediaDirs.firstOrNull()?.let { File(it, root) }
            if (file?.isDirectory!!) {
                listFile = file.listFiles()!!
                for (element in listFile) {
                    listofiles.add(element.absolutePath)
                }

            }
            //after completion we are setting the adapter and displaying it
        }.invokeOnCompletion {
            setAdapter()
        }


    }

    fun setAdapter() {
        CoroutineScope(Dispatchers.Main).launch {
            val gridLayoutManager = GridLayoutManager(this@DisplayGalleryActivity, 3)
            val adapter = DisplayAdapter(listofiles, this@DisplayGalleryActivity)
            shwo_recyclerview.adapter = adapter
           shwo_recyclerview.layoutManager = gridLayoutManager
        }
    }
// on clicking each image we ar previewing it in another activity
    override fun display(position: Int, p: String) {
   val intent1 = Intent(this, DisplayImageActivity::class.java)
//passing the path of the file
        intent1.putExtra("passing", p)
        Log.d("bitmap", "" + p)
        Toast.makeText(this, "posotion " + position +p, Toast.LENGTH_SHORT).show()

        startActivity(intent1)

    }

}