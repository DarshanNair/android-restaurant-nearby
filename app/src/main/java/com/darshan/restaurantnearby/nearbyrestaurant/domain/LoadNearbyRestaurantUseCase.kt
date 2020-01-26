package com.darshan.restaurantnearby.nearbyrestaurant.domain

import com.darshan.restaurantnearby.core.domain.UseCase
import com.darshan.restaurantnearby.core.network.model.NearbyRestaurant

interface LoadNearbyRestaurantUseCase : UseCase {

    fun execute(currentLatLong: String)

    fun setCallback(callback: Callback)

    interface Callback {
        fun onNearbyRestaurantFetchSuccess(nearbyRestaurantsData: NearbyRestaurant.Data)
        fun onNearbyRestaurantFetchError(throwable: Throwable)
    }

}