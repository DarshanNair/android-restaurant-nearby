package com.darshan.restaurantnearby.restaurantdetail.injection

import com.darshan.restaurantnearby.core.injection.scopes.PerActivity
import com.darshan.restaurantnearby.restaurantdetail.view.RestaurantDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RestaurantDetailActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [RestaurantDetailActivityModule::class])
    abstract fun bindRestaurantDetailActivity(): RestaurantDetailActivity

}