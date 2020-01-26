package com.darshan.restaurantnearby.nearbyrestaurant.manager.injection

import android.content.Context
import android.location.LocationManager
import com.darshan.restaurantnearby.core.injection.scopes.PerActivity
import dagger.Module
import dagger.Provides

@Module
class GeoLocationManagerModule {

    @Provides
    @PerActivity
    fun provideLocationManager(context: Context) =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

}