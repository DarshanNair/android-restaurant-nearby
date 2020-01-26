package com.darshan.restaurantnearby.restaurantdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darshan.restaurantnearby.restaurantdetail.domain.LoadRestaurantDetailUseCase
import javax.inject.Inject

class RestaurantDetailViewModelFactory @Inject constructor(
    private val loadRestaurantDetailUseCase: LoadRestaurantDetailUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RestaurantDetailViewModelImpl(
            loadRestaurantDetailUseCase
        ) as T

}