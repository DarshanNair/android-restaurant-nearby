package com.darshan.restaurantnearby.nearbyrestaurant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.darshan.restaurantnearby.core.network.model.NearbyRestaurant
import com.darshan.restaurantnearby.nearbyrestaurant.domain.LoadNearbyRestaurantUseCase

abstract class NearbyRestaurantViewModel : ViewModel(), LoadNearbyRestaurantUseCase.Callback {

    sealed class State {
        object Loading : State()
        data class Success(val nearbyRestaurantData: NearbyRestaurant.Data) : State()
        object Empty : State()
        object Error : State()
    }

    abstract fun state(): LiveData<State>

    abstract fun loadNearbyRestaurants(currentLatLong: String)

}