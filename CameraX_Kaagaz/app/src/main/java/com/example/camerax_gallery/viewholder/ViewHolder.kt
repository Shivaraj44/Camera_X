package com.example.camerax_gallery.viewholder

import android.graphics.BitmapFactory
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_gallery.DataBase.Model
import kotlinx.android.synthetic.main.item.view.*
import java.io.File


class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun SetDataHome(model: Model){
            view.apply {

                val imgFile: File = File(model.uri)
                if (imgFile.exists()) {
                    val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)

                    recycler_image.setImageBitmap(myBitmap)
                }
            }
        }
    }