package com.darshan.restaurantnearby.restaurantdetail.domain

import com.darshan.restaurantnearby.core.domain.BaseUseCase
import com.darshan.restaurantnearby.core.injection.qualifiers.ForIoThread
import com.darshan.restaurantnearby.core.injection.qualifiers.ForMainThread
import com.darshan.restaurantnearby.core.network.model.RestaurantDetail
import com.darshan.restaurantnearby.restaurantdetail.domain.LoadRestaurantDetailUseCase.Callback
import com.darshan.restaurantnearby.restaurantdetail.repository.LoadRestaurantDetailRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoadRestaurantDetailUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val loadRestaurantDetailRepository: LoadRestaurantDetailRepository,
    @ForIoThread private val ioScheduler: Scheduler,
    @ForMainThread private val mainScheduler: Scheduler
) : BaseUseCase(compositeDisposable), LoadRestaurantDetailUseCase {

    private var callback: Callback? = null

    override fun execute(id: String) {
        trackDisposable(
            loadRestaurantDetailRepository.getRestaurantDetail(id)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(::onSuccess, ::onError)
        )
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

    private fun onSuccess(nearbyRestaurantData: RestaurantDetail.Data) {
        callback?.onRestaurantDetailFetchSuccess(nearbyRestaurantData)
    }

    private fun onError(throwable: Throwable) {
        callback?.onRestaurantDetailFetchError(throwable)
    }

    override fun cleanup() {
        callback = null
        super.cleanup()
    }

}