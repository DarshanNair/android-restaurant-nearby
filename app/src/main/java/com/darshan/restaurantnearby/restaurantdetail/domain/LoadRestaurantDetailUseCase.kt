package com.darshan.restaurantnearby.restaurantdetail.domain

import com.darshan.restaurantnearby.core.domain.UseCase
import com.darshan.restaurantnearby.core.network.model.RestaurantDetail

interface LoadRestaurantDetailUseCase : UseCase {

    fun execute(id: String)

    fun setCallback(callback: Callback)

    interface Callback {
        fun onRestaurantDetailFetchSuccess(restaurantDetailData: RestaurantDetail.Data)
        fun onRestaurantDetailFetchError(throwable: Throwable)
    }

}