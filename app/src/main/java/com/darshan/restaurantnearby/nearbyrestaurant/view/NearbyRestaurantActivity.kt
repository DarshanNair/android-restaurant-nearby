package com.darshan.restaurantnearby.nearbyrestaurant.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.darshan.restaurantnearby.R
import com.darshan.restaurantnearby.core.network.model.NearbyRestaurant
import com.darshan.restaurantnearby.nearbyrestaurant.manager.GeoLocationManager
import com.darshan.restaurantnearby.nearbyrestaurant.viewmodel.NearbyRestaurantViewModel
import com.darshan.restaurantnearby.nearbyrestaurant.viewmodel.NearbyRestaurantViewModel.State
import com.darshan.restaurantnearby.restaurantdetail.view.RestaurantDetailActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_nearby_restaurant.*
import javax.inject.Inject


class NearbyRestaurantActivity : AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var nearbyRestaurantViewModel: NearbyRestaurantViewModel
    @Inject
    lateinit var geoLocationManager: GeoLocationManager

    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_nearby_restaurant)

        nearbyRestaurantViewModel.state().observe(
            this@NearbyRestaurantActivity,
            Observer { it?.let { onNearbyRestaurantsLoaded(it) } }
        )

        initializeMap()
    }

    private fun onNearbyRestaurantsLoaded(state: State) {
        when (state) {
            State.Loading -> {
                progress_bar.visibility = View.VISIBLE
            }
            is State.Success -> {
                progress_bar.visibility = View.GONE
                setContent(state.nearbyRestaurantData)
            }
            State.Empty -> {
            }
            State.Error -> {
            }
        }
    }

    private fun initializeMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        this.googleMap.uiSettings.isMapToolbarEnabled = false
        setMyLocationEnabled()

        googleMap.setOnCameraMoveListener { handleOnCameraIdleState() }
        googleMap.setOnInfoWindowClickListener { marker ->
            startActivity(
                RestaurantDetailActivity.getStartIntent(
                    this@NearbyRestaurantActivity,
                    marker.snippet
                )
            )
            marker.hideInfoWindow()
        }
    }

    companion object {
        private const val REQUEST_LOCATION_APP_PERMISSION = 4
    }

    private fun setMyLocationEnabled() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
            findNearbyRestaurantsOnLaunch()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_APP_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_LOCATION_APP_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setMyLocationEnabled()
                } else {
                }
                return
            }
        }
    }

    private fun handleOnCameraIdleState() {
        /*nearbyRestaurantViewModel.loadNearbyRestaurants(googleMap.projection.visibleRegion.)*/
    }

    private fun setContent(nearbyRestaurantData: NearbyRestaurant.Data) {
        val markerOptions = MarkerOptions()
        for (venue in nearbyRestaurantData.response.venues) {
            val latLng = LatLng(venue.location.latitude, venue.location.longitude)
            markerOptions.position(latLng)
            markerOptions.title(venue.name)
            markerOptions.snippet(venue.id)
            markerOptions.infoWindowAnchor(0.5f, 2.7f)
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_marker))
            googleMap.addMarker(markerOptions)
        }
    }

    private fun findNearbyRestaurantsOnLaunch() {
        val location = geoLocationManager.getLastLocation()
        if (location != null) {
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(location.latitude, location.longitude),
                    15.0f
                )
            )
            nearbyRestaurantViewModel.loadNearbyRestaurants("" + location.latitude + "," + location.longitude)
        }
    }


}
