package pl.gmat.roomdetector.ui

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import pl.gmat.roomdetector.R
import pl.gmat.roomdetector.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        checkPermissions()
    }

    private fun setupBinding() {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .run {
                viewModel = this@MainActivity.viewModel
                lifecycleOwner = this@MainActivity
            }
    }

    private fun checkPermissions() {
        val permissions =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                arrayOf(
                    BLUETOOTH_SCAN,
                    BLUETOOTH_CONNECT,
                    ACCESS_FINE_LOCATION,
                )
            } else {
                arrayOf(ACCESS_FINE_LOCATION)
            }
        if (permissions.any { !permissionGranted(it) }) {
            ActivityCompat.requestPermissions(this, permissions, 100)
        }
    }

    private fun permissionGranted(permission: String) =
        ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}
