package com.darshan.restaurantnearby.nearbyrestaurant.repository

import com.darshan.restaurantnearby.core.network.model.NearbyRestaurant
import io.reactivex.Single

interface LoadNearbyRestaurantRepository {

    fun getNearbyRestaurant(
        currentLatLong: String,
        intent: String,
        categoryId: String
    ): Single<NearbyRestaurant.Data>

}