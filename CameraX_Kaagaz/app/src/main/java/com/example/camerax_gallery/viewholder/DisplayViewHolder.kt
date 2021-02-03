package com.example.camerax_gallery.viewholder

import android.graphics.BitmapFactory
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_gallery.listner.DisplayListner
import kotlinx.android.synthetic.main.item.view.*


class DisplayViewHolder(var view: View,private  var listner: DisplayListner):RecyclerView.ViewHolder(view) {
fun setdat(p:String){
    view.apply {
        val bitmap=BitmapFactory.decodeFile(p)
        recycler_image.setImageBitmap(bitmap)
        recycler_image.setOnClickListener {
            listner.display(adapterPosition,p)
        }
    }


}


}