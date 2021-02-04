package com.example.camerax_gallery.Activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.camerax_gallery.R
import com.example.camerax_gallery.viewmodel.UserViewModel
import com.example.camerax_gallery.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

/**
CameraX is used in this Activity where we will first bind the lifecycle of the camera to
lifecycle of the activity and then we will overlap the camera to the xml
 */
class MainActivity : AppCompatActivity() {
    var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    val arrayList = ArrayList<String>()

    private lateinit var cameraExecutor: ExecutorService

    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = UserViewModelFactory(this).create(UserViewModel::class.java)


        //Opens another activity where we will display all the albums in the gallery
        open_gallery.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, ListDirectoryActivity::class.java)

            startActivity(intent)

        })

        // Requesting camera permissions
        if (allPermissionsGranted()) {
            //if granted then start camera
            startCamera()
        } else {
            //request the permission
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        // Onclicking of button your are taking photo
        camera_capture_button.setOnClickListener {
            takePhoto()
        }

        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()
    }


    // To Grant all permission required
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    /*
     Onclicking of button your are taking photo and storing in the local storage by creating a directry
    which was getting fro the HomeActivity
    */
    private fun getOutputDirectory(): File {
        val directoryName = intent.getStringExtra("directoryName")
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, directoryName).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    //complete destroy
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraXBasic"
        const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    /*
    Taking the photo and storing into the directory which we have created using the getOutputDirectory.
    we are storing the directory name and currentTimeStamp with .jpg to it.
     */
    fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create time-stamped output file to hold the image
        val storing_image = File(
            outputDirectory, SimpleDateFormat(
                FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(storing_image).build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.d("failure", "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(storing_image)


//                    viewModel.insertDataToDatabase(a, savedUri.toString())


                    val msg = "Photo capture succeeded: $savedUri"
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                    // Log.d(MainActivity.TAG, msg)
                }
            })
    }

    //binding the lifecycle of cameraX and activity. Overlapping the camera hardware into the xml where
    // the camera will be visible in the background
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview to the surface of xml file.Its a Listner which updates the ui everytime the output changes.
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(Livecamera.createSurfaceProvider())
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
//                    it.setAnalyzer(cameraExecutor, LuminosityAnalyzer { luma ->
//                        Log.d(TAG, "Average luminosity: $luma")
//                    })
                }

            // Select back camera as a default
            var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture, imageAnalyzer
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }
}