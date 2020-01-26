package com.darshan.restaurantnearby.restaurantdetail.domain.injection

import com.darshan.restaurantnearby.restaurantdetail.domain.LoadRestaurantDetailUseCase
import com.darshan.restaurantnearby.restaurantdetail.domain.LoadRestaurantDetailUseCaseImpl
import com.darshan.restaurantnearby.restaurantdetail.repository.injection.LoadRestaurantDetailRepositoryModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(
    includes = [
        LoadRestaurantDetailRepositoryModule::class
    ]
)
class LoadRestaurantDetailUseCaseModule {

    @Provides
    fun provideLoadRestaurantDetailUseCase(usecase: LoadRestaurantDetailUseCaseImpl): LoadRestaurantDetailUseCase =
        usecase

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

}
