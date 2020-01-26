package com.darshan.restaurantnearby.core.network.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Nullable;

@AutoValue
public abstract class NearbyRestaurant {

    @AutoValue
    public static abstract class Data {

        public static TypeAdapter<Data> typeAdapter(Gson gson) {
            return new AutoValue_NearbyRestaurant_Data.GsonTypeAdapter(gson);
        }

        @SerializedName("response")
        public abstract Response getResponse();
    }

    @AutoValue
    public static abstract class Response {

        public static TypeAdapter<Response> typeAdapter(Gson gson) {
            return new AutoValue_NearbyRestaurant_Response.GsonTypeAdapter(gson);
        }

        @SerializedName("venues")
        public abstract List<Venue> getVenues();

    }

    @AutoValue
    public static abstract class Venue {

        public static TypeAdapter<Venue> typeAdapter(Gson gson) {
            return new AutoValue_NearbyRestaurant_Venue.GsonTypeAdapter(gson);
        }

        @SerializedName("id")
        public abstract String getId();

        @SerializedName("name")
        public abstract String getName();

        @SerializedName("location")
        public abstract Location getLocation();

    }

    @AutoValue
    public static abstract class Location {

        public static TypeAdapter<Location> typeAdapter(Gson gson) {
            return new AutoValue_NearbyRestaurant_Location.GsonTypeAdapter(gson);
        }

        @Nullable
        @SerializedName("address")
        public abstract String getAddress();

        @SerializedName("lat")
        public abstract double getLatitude();

        @SerializedName("lng")
        public abstract double getLongitude();

    }

}
