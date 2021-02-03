package com.example.camerax_gallery.Activities

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.camerax_gallery.R
import kotlinx.android.synthetic.main.activity_display_image.*


class DisplayImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_image)


        val p = intent.getStringExtra("passing")
        val myBitmap = BitmapFactory.decodeFile(p)
        display_image.setImageBitmap(myBitmap)


        // val path=intent.getStringExtra("passing")
        // val bmp: Bitmap

        //    val byteArray = intent.getByteArrayExtra("image")
        //  bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
//val data=intent.getStringExtra("passing")
        // val bitmap = intent.getParcelableExtra<Parcelable>("passing") as Bitmap?
        //val myBitmap = BitmapFactory.decodeFile(bmp)
        //holder.imageview.setImageBitmap(myBitmap)
        // display_image.setImageBitmap(bmp)
//val name: String? =intent.getStringExtra("filename")
//        val path:String= Environment.getStorageDirectory() + name + ".png"
//        val bm = BitmapFactory.decodeFile(path)
//        display_image.setImageBitmap(bm)


        // val extras= Intent()
        //  val byteArray= extras.getByteArrayExtra("PICTURE")

        //  val byteArray: ByteArray = intent.getByteArray("PICTURE")
        //   val bitmap = byteArray?.size?.let { BitmapFactory.decodeByteArray(byteArray, 0, it) }

//        if (intent.hasExtra("byteArray")) {
//            val previewThumbnail = ImageView(this)
//            val b = BitmapFactory.decodeByteArray(
//                intent.getByteArrayExtra("byteArray"),
//                0,
//                intent.getByteArrayExtra("byteArray")!!.size
//            )
//        val bitmap = intent.getParcelableExtra<Bitmap>("bitmap")
    }

}