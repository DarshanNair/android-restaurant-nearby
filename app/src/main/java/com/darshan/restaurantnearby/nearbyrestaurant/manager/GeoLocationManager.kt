package com.darshan.restaurantnearby.nearbyrestaurant.manager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import dagger.Module
import javax.inject.Inject

@Module
class GeoLocationManager @Inject constructor(
    private val context: Context,
    private val manager: LocationManager
) {

    fun getLastLocation(): Location? {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (getLocationProvider(manager) != null) {
                return if (getLocationProvider(manager).equals(
                        LocationManager.GPS_PROVIDER,
                        ignoreCase = true
                    )
                )
                    manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                else
                    manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }
        }
        return null
    }

    private fun getLocationProvider(manager: LocationManager): String? {
        if (manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER
        } else if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER
        }
        return null
    }

}