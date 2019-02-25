package com.example.location

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), LocationListener {

    override fun onLocationChanged(location: Location?) {
        var gecoder = Geocoder(this, Locale.getDefault())
        var addressList = gecoder.getFromLocation(location!!.latitude, location!!.longitude, 1)
        var cityName = addressList.get(0).getAddressLine(0)
        txtViewLocation.setText(cityName)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestLocationUsingLocationManager()

    }

    private fun requestLocationUsingLocationManager() {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0f, this
            )
        } catch (ex: SecurityException) {
            ex.printStackTrace()
        }
    }

    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }
}
