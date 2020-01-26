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
        @Query("v") v: String = "20130815",
        @Query("intent") intent: String = "checkin",
        @Query("categoryId") categoryId: String = "4d4b7105d754a06374d81259",
        @Query("client_id") client_id: String = "C5UATRQ4YHCUKOODPTLNWC1ALUT33WNB5BR1SEVAPRW4A140",
        @Query("client_secret") client_secret: String = "PO00FLMNZKOETZO3IOXLQLFA53BKJIVHD3IXFVWH24YJS5SY"
    ): Single<NearbyRestaurant.Data>

    @GET("{id}")
    fun getRestaurantDetail(
        @Path("id") id: String,
        @Query("v") v: String = "20130815",
        @Query("client_id") client_id: String = "C5UATRQ4YHCUKOODPTLNWC1ALUT33WNB5BR1SEVAPRW4A140",
        @Query("client_secret") client_secret: String = "PO00FLMNZKOETZO3IOXLQLFA53BKJIVHD3IXFVWH24YJS5SY"
    ): Single<RestaurantDetail.Data>

}