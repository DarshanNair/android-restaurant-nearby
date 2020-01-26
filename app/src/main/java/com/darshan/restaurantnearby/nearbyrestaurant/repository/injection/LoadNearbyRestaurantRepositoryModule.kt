package com.darshan.restaurantnearby.nearbyrestaurant.repository.injection

import com.darshan.restaurantnearby.nearbyrestaurant.repository.LoadNearbyRestaurantRepository
import com.darshan.restaurantnearby.nearbyrestaurant.repository.LoadNearbyRestaurantRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class LoadNearbyRestaurantRepositoryModule {

    @Provides
    fun provideLoadNearbyRestaurantRepository(repository: LoadNearbyRestaurantRepositoryImpl): LoadNearbyRestaurantRepository =
        repository

}