package com.darshan.restaurantnearby.restaurantdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.darshan.restaurantnearby.core.network.model.RestaurantDetail
import com.darshan.restaurantnearby.restaurantdetail.domain.LoadRestaurantDetailUseCase

abstract class RestaurantDetailViewModel : ViewModel(), LoadRestaurantDetailUseCase.Callback {

    sealed class State {
        object Loading : State()
        data class Success(val restaurantDetailData: RestaurantDetail.Data) : State()
        object Empty : State()
        object Error : State()
    }

    abstract fun state(): LiveData<State>

    abstract fun loadRestaurantDetail(id: String)

}