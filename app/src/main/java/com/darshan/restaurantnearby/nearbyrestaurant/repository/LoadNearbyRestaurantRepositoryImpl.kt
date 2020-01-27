package com.darshan.restaurantnearby.nearbyrestaurant.repository

import com.darshan.restaurantnearby.core.network.api.FourSquareApi
import com.darshan.restaurantnearby.core.network.model.NearbyRestaurant
import io.reactivex.Single
import javax.inject.Inject

class LoadNearbyRestaurantRepositoryImpl @Inject constructor(
    private val fourSquareApi: FourSquareApi
) : LoadNearbyRestaurantRepository {

    override fun getNearbyRestaurant(
        currentLatLong: String,
        intent: String,
        categoryId: String
    ): Single<NearbyRestaurant.Data> =
        fourSquareApi.getNearbyRestaurants(currentLatLong, intent, categoryId)

}