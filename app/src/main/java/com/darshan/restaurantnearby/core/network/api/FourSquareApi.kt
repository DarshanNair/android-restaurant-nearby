package com.darshan.restaurantnearby.core.network.api

import com.darshan.restaurantnearby.core.network.model.NearbyRestaurant
import com.darshan.restaurantnearby.core.network.model.RestaurantDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FourSquareApi {

    @GET("search")
    fun getNearbyRestaurants(
        @Query("ll", encoded = true) ll: String,
        @Query("intent") intent: String,
        @Query("categoryId") categoryId: String
    ): Single<NearbyRestaurant.Data>

    @GET("{id}")
    fun getRestaurantDetail(
        @Path("id") id: String
    ): Single<RestaurantDetail.Data>

}