package com.darshan.restaurantnearby.restaurantdetail.repository.injection

import com.darshan.restaurantnearby.restaurantdetail.repository.LoadRestaurantDetailRepository
import com.darshan.restaurantnearby.restaurantdetail.repository.LoadRestaurantDetailRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class LoadRestaurantDetailRepositoryModule {

    @Provides
    fun provideLoadRestaurantDetailRepository(repository: LoadRestaurantDetailRepositoryImpl): LoadRestaurantDetailRepository =
        repository

}