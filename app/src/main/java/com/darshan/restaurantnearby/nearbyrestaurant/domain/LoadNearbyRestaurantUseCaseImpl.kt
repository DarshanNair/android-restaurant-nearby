package com.darshan.restaurantnearby.nearbyrestaurant.domain

import com.darshan.restaurantnearby.core.domain.BaseUseCase
import com.darshan.restaurantnearby.core.injection.qualifiers.ForIoThread
import com.darshan.restaurantnearby.core.injection.qualifiers.ForMainThread
import com.darshan.restaurantnearby.core.network.model.NearbyRestaurant
import com.darshan.restaurantnearby.nearbyrestaurant.domain.LoadNearbyRestaurantUseCase.Callback
import com.darshan.restaurantnearby.nearbyrestaurant.repository.LoadNearbyRestaurantRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoadNearbyRestaurantUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val loadNearbyRestaurantRepository: LoadNearbyRestaurantRepository,
    @ForIoThread private val ioScheduler: Scheduler,
    @ForMainThread private val mainScheduler: Scheduler
) : BaseUseCase(compositeDisposable), LoadNearbyRestaurantUseCase {

    private var callback: Callback? = null

    companion object {
        private const val INTENT_VALUE = "checkin"
        private const val FOOD_CATEGORY_VALUE = "4d4b7105d754a06374d81259"
    }

    override fun execute(currentLatLong: String) {
        trackDisposable(
            loadNearbyRestaurantRepository.getNearbyRestaurant(currentLatLong, INTENT_VALUE, FOOD_CATEGORY_VALUE)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(::onSuccess, ::onError)
        )
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

    private fun onSuccess(nearbyRestaurantData: NearbyRestaurant.Data) {
        callback?.onNearbyRestaurantFetchSuccess(nearbyRestaurantData)
    }

    private fun onError(throwable: Throwable) {
        callback?.onNearbyRestaurantFetchError(throwable)
    }

    override fun cleanup() {
        callback = null
        super.cleanup()
    }

}