package com.example.camerax_gallery.Activities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.camerax_gallery.R
import kotlinx.android.synthetic.main.activity_display_image.*

/**
decoding the bitmap and displaying the image from the selected image
 */
class DisplayImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_image)


        val p = intent.getStringExtra("passing")
        val myBitmap = BitmapFactory.decodeFile(p)


        display_image.setImageBitmap(rotateBitmap(myBitmap))



    }
    fun rotateBitmap(bitmap: Bitmap): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(90F)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }


}