package com.darshan.restaurantnearby.nearbyrestaurant.injection

import com.darshan.restaurantnearby.core.injection.scopes.PerActivity
import com.darshan.restaurantnearby.nearbyrestaurant.view.NearbyRestaurantActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NearbyRestaurantActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [NearbyRestaurantActivityModule::class])
    abstract fun bindNearbyRestaurantActivity(): NearbyRestaurantActivity

}