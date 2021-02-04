package com.example.camerax_gallery.viewholder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_gallery.listner.DisplayListner
import kotlinx.android.synthetic.main.item.view.*


class DisplayViewHolder(var view: View, private var listner: DisplayListner):RecyclerView.ViewHolder(
    view
) {
fun setdat(p: String){
    view.apply {
        val bitmap=BitmapFactory.decodeFile(p)

        recycler_image.setImageBitmap(rotateBitmap(bitmap))
        recycler_image.setOnClickListener {
            listner.display(adapterPosition, p)

        }
    }


}
    // Rotating image by 90 degree
    fun rotateBitmap(bitmap: Bitmap): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(90F)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }


}