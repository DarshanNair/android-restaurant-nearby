package com.darshan.restaurantnearby.restaurantdetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.darshan.restaurantnearby.R
import com.darshan.restaurantnearby.core.network.model.RestaurantDetail
import com.darshan.restaurantnearby.restaurantdetail.viewmodel.RestaurantDetailViewModel.State
import com.darshan.restaurantnearby.restaurantdetail.viewmodel.RestaurantDetailViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import kotlinx.android.synthetic.main.view_restaurant_detail_loaded.*
import javax.inject.Inject

class RestaurantDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var restaurantDetailViewModel: RestaurantDetailViewModel

    companion object {
        private const val KEY_VENUE_ID = "KEY_VENUE_ID"

        fun getStartIntent(context: Context, id: String) =
            Intent(context, RestaurantDetailActivity::class.java).putExtra(
                KEY_VENUE_ID, id
            )
    }

    enum class UIState {
        LOADING,
        LOADED,
        ERROR
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_restaurant_detail)

        restaurantDetailViewModel.apply {
            state().observe(this@RestaurantDetailActivity, Observer { it?.let { onRestaurantDetailLoaded(it) } })
            loadRestaurantDetail(getID())
        }
    }

    private fun getID() = intent.getStringExtra(KEY_VENUE_ID) as String

    private fun onRestaurantDetailLoaded(state: State) {
        when (state) {
            State.Loading -> view_flipper_restaurant_detail.displayedChild = UIState.LOADING.ordinal
            is State.Success -> {
                view_flipper_restaurant_detail.displayedChild = UIState.LOADED.ordinal
                setData(state.restaurantDetailData)
            }
            State.Error -> view_flipper_restaurant_detail.displayedChild = UIState.ERROR.ordinal
        }
    }

    private fun setData(restaurantDetailData: RestaurantDetail.Data) {
        with(restaurantDetailData.response.venue) {
            venue_name.text = restaurantDetailData.response.venue.name
            venue_phone.text = restaurantDetailData.response.venue.contact.phone
            venue_twitter.text = restaurantDetailData.response.venue.contact.twitter
            venue_facebook.text = restaurantDetailData.response.venue.contact.facebookName
            venue_address.text = restaurantDetailData.response.venue.location.address
            venue_cross_street.text = restaurantDetailData.response.venue.location.crossStreet
        }
    }

}
