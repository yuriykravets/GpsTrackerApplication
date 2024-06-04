package com.partitionsoft.gpstrackerapplication.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.partitionsoft.gpstrackerapplication.R
import com.partitionsoft.gpstrackerapplication.data.network.GpsLocationCallback
import com.partitionsoft.gpstrackerapplication.common.viewBinding
import com.partitionsoft.gpstrackerapplication.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val locationViewModel: LocationViewModel by viewModel()
    private val gpsLocationCallback: GpsLocationCallback by inject { parametersOf(locationViewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeLocations()
        setupListeners()
        checkLocationPermissionAndStartUpdates()
    }

    private fun observeLocations() {
        locationViewModel.locations.observe(this) { locations ->
            if (locations.isNotEmpty()) {
                val lastLocations = locations.takeLast(10)
                val text = lastLocations.joinToString("\n") { location ->
                    "Lat: ${location.latitude}, Long: ${location.longitude}"
                }
                binding.tvLocationTracker.text = text
            }
        }
    }

    private fun setupListeners() {
        binding.btnLocationTracker.setOnClickListener {
            requestLocationPermission()
        }
    }

    private fun checkLocationPermissionAndStartUpdates() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            startLocationUpdates()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }


    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val locationRequest = LocationRequest.Builder(LOCATION_UPDATE_INTERVAL).apply {
            setPriority(Priority.PRIORITY_HIGH_ACCURACY)
        }.build()
        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(locationRequest, gpsLocationCallback, Looper.getMainLooper())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                startLocationUpdates()
                binding.btnLocationTracker.visibility = android.view.View.GONE
            } else {
                binding.tvLocationTracker.text = getString(R.string.location_tracker_warning)
                binding.tvLocationTracker.gravity = android.view.Gravity.CENTER_HORIZONTAL
                binding.btnLocationTracker.visibility = android.view.View.VISIBLE
            }
        }
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        const val LOCATION_UPDATE_INTERVAL = 300000L
    }
}
