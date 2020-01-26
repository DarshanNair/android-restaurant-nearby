package com.darshan.restaurantnearby.nearbyrestaurant.injection

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.darshan.restaurantnearby.core.injection.scopes.PerActivity
import com.darshan.restaurantnearby.nearbyrestaurant.domain.injection.LoadNearbyRestaurantUseCaseModule
import com.darshan.restaurantnearby.nearbyrestaurant.manager.injection.GeoLocationManagerModule
import com.darshan.restaurantnearby.nearbyrestaurant.view.NearbyRestaurantActivity
import com.darshan.restaurantnearby.nearbyrestaurant.viewmodel.NearbyRestaurantViewModel
import com.darshan.restaurantnearby.nearbyrestaurant.viewmodel.NearbyRestaurantViewModelFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        LoadNearbyRestaurantUseCaseModule::class,
        GeoLocationManagerModule::class
    ]
)
class NearbyRestaurantActivityModule {

    @Provides
    @PerActivity
    fun provideContext(activity: NearbyRestaurantActivity): Context = activity

    @Provides
    @PerActivity
    fun provideTopTrendingListViewModel(
        activity: NearbyRestaurantActivity,
        factory: NearbyRestaurantViewModelFactory
    ): NearbyRestaurantViewModel =
        ViewModelProviders.of(activity, factory).get(NearbyRestaurantViewModel::class.java)

}