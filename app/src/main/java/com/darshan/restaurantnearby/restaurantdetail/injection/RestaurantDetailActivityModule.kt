package com.darshan.restaurantnearby.restaurantdetail.injection

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.darshan.restaurantnearby.core.injection.scopes.PerActivity
import com.darshan.restaurantnearby.nearbyrestaurant.view.NearbyRestaurantActivity
import com.darshan.restaurantnearby.nearbyrestaurant.viewmodel.NearbyRestaurantViewModelFactory
import com.darshan.restaurantnearby.restaurantdetail.domain.injection.LoadRestaurantDetailUseCaseModule
import com.darshan.restaurantnearby.restaurantdetail.view.RestaurantDetailActivity
import com.darshan.restaurantnearby.restaurantdetail.viewmodel.RestaurantDetailViewModel
import com.darshan.restaurantnearby.restaurantdetail.viewmodel.RestaurantDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        LoadRestaurantDetailUseCaseModule::class
    ]
)
class RestaurantDetailActivityModule {

    @Provides
    @PerActivity
    fun provideContext(activity: NearbyRestaurantActivity): Context = activity

    @Provides
    @PerActivity
    fun provideRestaurantDetailViewModel(
        activity: RestaurantDetailActivity,
        factory: RestaurantDetailViewModelFactory
    ): RestaurantDetailViewModel =
        ViewModelProviders.of(activity, factory).get(RestaurantDetailViewModel::class.java)

}