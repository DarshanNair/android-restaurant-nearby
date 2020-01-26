package com.darshan.restaurantnearby.nearbyrestaurant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.darshan.restaurantnearby.core.network.model.NearbyRestaurant
import com.darshan.restaurantnearby.nearbyrestaurant.domain.LoadNearbyRestaurantUseCase
import javax.inject.Inject

class NearbyRestaurantViewModelImpl @Inject internal constructor(
    private val loadNearbyRestaurantUseCase: LoadNearbyRestaurantUseCase
) : NearbyRestaurantViewModel() {

    private val stateLiveData = MediatorLiveData<NearbyRestaurantViewModel.State>()

    init {
        loadNearbyRestaurantUseCase.setCallback(this)
    }

    override fun state(): LiveData<State> = stateLiveData

    override fun loadNearbyRestaurants(currentLatLong: String) {
        stateLiveData.value = State.Loading
        loadNearbyRestaurantUseCase.execute(currentLatLong)
    }

    override fun onNearbyRestaurantFetchSuccess(nearbyRestaurantsData: NearbyRestaurant.Data) {
        if (nearbyRestaurantsData.response.venues.isEmpty()) {
            stateLiveData.value = State.Empty
        } else {
            stateLiveData.value = State.Success(nearbyRestaurantsData)
        }
    }

    override fun onNearbyRestaurantFetchError(throwable: Throwable) {
        stateLiveData.value = State.Error
    }

    public override fun onCleared() {
        super.onCleared()
        loadNearbyRestaurantUseCase.cleanup()
    }

}