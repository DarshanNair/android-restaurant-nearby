package com.darshan.restaurantnearby.core.injection

import android.app.Application
import com.darshan.restaurantnearby.core.NearbyRestaurantApplication
import com.darshan.restaurantnearby.core.injection.scopes.PerApplication
import com.darshan.restaurantnearby.core.network.api.injection.FourSquareApiModule
import com.darshan.restaurantnearby.nearbyrestaurant.injection.NearbyRestaurantActivityBuilderModule
import com.darshan.restaurantnearby.restaurantdetail.injection.RestaurantDetailActivityBuilderModule
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(
    modules = [
        BaseModule::class,
        FourSquareApiModule::class,
        NearbyRestaurantActivityBuilderModule::class,
        RestaurantDetailActivityBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: NearbyRestaurantApplication)

}