package com.darshan.restaurantnearby.restaurantdetail.repository

import com.darshan.restaurantnearby.core.network.model.RestaurantDetail
import io.reactivex.Single

interface LoadRestaurantDetailRepository {

    fun getRestaurantDetail(id: String): Single<RestaurantDetail.Data>

}