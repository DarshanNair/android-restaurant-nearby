package com.darshan.restaurantnearby.restaurantdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.darshan.restaurantnearby.core.network.model.RestaurantDetail
import com.darshan.restaurantnearby.restaurantdetail.domain.LoadRestaurantDetailUseCase
import javax.inject.Inject

class RestaurantDetailViewModelImpl @Inject internal constructor(
    private val loadRestaurantDetailUseCase: LoadRestaurantDetailUseCase
) : RestaurantDetailViewModel() {

    private val stateLiveData = MediatorLiveData<RestaurantDetailViewModel.State>()

    init {
        loadRestaurantDetailUseCase.setCallback(this)
    }

    override fun state(): LiveData<State> = stateLiveData

    override fun loadRestaurantDetail(id: String) {
        stateLiveData.value = State.Loading
        loadRestaurantDetailUseCase.execute(id)
    }

    override fun onRestaurantDetailFetchSuccess(restaurantDetailData: RestaurantDetail.Data) {
        stateLiveData.value = State.Success(restaurantDetailData)
    }

    override fun onRestaurantDetailFetchError(throwable: Throwable) {
        stateLiveData.value = State.Error
    }

    public override fun onCleared() {
        super.onCleared()
        loadRestaurantDetailUseCase.cleanup()
    }

}