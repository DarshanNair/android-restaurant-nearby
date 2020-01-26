package com.darshan.restaurantnearby.restaurantdetail.repository

import com.darshan.restaurantnearby.core.network.api.FourSquareApi
import com.darshan.restaurantnearby.core.network.model.RestaurantDetail
import io.reactivex.Single
import javax.inject.Inject

class LoadRestaurantDetailRepositoryImpl @Inject constructor(
    private val fourSquareApi: FourSquareApi
) : LoadRestaurantDetailRepository {

    override fun getRestaurantDetail(id: String): Single<RestaurantDetail.Data> =
        fourSquareApi.getRestaurantDetail(id)

}