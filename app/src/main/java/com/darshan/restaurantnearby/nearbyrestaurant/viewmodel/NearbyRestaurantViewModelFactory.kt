package com.darshan.restaurantnearby.nearbyrestaurant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darshan.restaurantnearby.nearbyrestaurant.domain.LoadNearbyRestaurantUseCase
import javax.inject.Inject

class NearbyRestaurantViewModelFactory @Inject constructor(
    private val loadNearbyRestaurantUseCase: LoadNearbyRestaurantUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        NearbyRestaurantViewModelImpl(
            loadNearbyRestaurantUseCase
        ) as T

}