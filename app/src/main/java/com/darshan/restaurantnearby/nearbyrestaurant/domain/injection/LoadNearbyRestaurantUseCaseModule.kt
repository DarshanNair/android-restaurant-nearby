package com.darshan.restaurantnearby.nearbyrestaurant.domain.injection

import com.darshan.restaurantnearby.nearbyrestaurant.domain.LoadNearbyRestaurantUseCase
import com.darshan.restaurantnearby.nearbyrestaurant.domain.LoadNearbyRestaurantUseCaseImpl
import com.darshan.restaurantnearby.nearbyrestaurant.repository.injection.LoadNearbyRestaurantRepositoryModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(
    includes = [
        LoadNearbyRestaurantRepositoryModule::class
    ]
)
class LoadNearbyRestaurantUseCaseModule {

    @Provides
    fun provideLoadNearbyRestaurantUseCase(usecase: LoadNearbyRestaurantUseCaseImpl): LoadNearbyRestaurantUseCase =
        usecase

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

}
