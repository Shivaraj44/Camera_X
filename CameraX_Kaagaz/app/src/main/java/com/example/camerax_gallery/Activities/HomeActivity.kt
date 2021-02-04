package com.example.camerax_gallery.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.camerax_gallery.R
import com.example.camerax_gallery.viewmodel.UserViewModel
import com.example.camerax_gallery.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*

/** In this we are creating album name entered by the user after clicking the floating button on the bottom
//after clicking the take photo button navigating to another activity where camera is opening
*/
class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    private val StringPATHv = "/storage/emulated/0/Downloads/image.png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = UserViewModelFactory(this).create(UserViewModel::class.java)


// On clicking this button a layout is visible where user will enter the album name
        floating_action_button.setOnClickListener(View.OnClickListener {

            linear_layout_home.visibility = View.VISIBLE
            save.setOnClickListener(View.OnClickListener {
                viewModel.insertalbumname(0, et_albumName.text.toString())

                //Opening MainActivity
                val intent = Intent(this@HomeActivity, MainActivity::class.java)

                // passing the album name to MainActivity
                intent.putExtra("directoryName", et_albumName.text.toString())
                startActivity(intent)
                linear_layout_home.visibility = View.GONE

            })

        })


    }

}