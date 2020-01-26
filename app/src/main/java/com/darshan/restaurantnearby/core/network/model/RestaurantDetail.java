package com.darshan.restaurantnearby.core.network.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class RestaurantDetail {

    @AutoValue
    public static abstract class Data {

        public static TypeAdapter<Data> typeAdapter(Gson gson) {
            return new AutoValue_RestaurantDetail_Data.GsonTypeAdapter(gson);
        }

        @SerializedName("response")
        public abstract Response getResponse();
    }

    @AutoValue
    public static abstract class Response {

        public static TypeAdapter<Response> typeAdapter(Gson gson) {
            return new AutoValue_RestaurantDetail_Response.GsonTypeAdapter(gson);
        }

        @SerializedName("venue")
        public abstract Venue getVenue();

    }

    @AutoValue
    public static abstract class Venue {

        public static TypeAdapter<Venue> typeAdapter(Gson gson) {
            return new AutoValue_RestaurantDetail_Venue.GsonTypeAdapter(gson);
        }

        @SerializedName("name")
        public abstract String getName();

        @SerializedName("contact")
        public abstract Contact getContact();

        @SerializedName("location")
        public abstract Location getLocation();

    }

    @AutoValue
    public static abstract class Contact {

        public static TypeAdapter<Contact> typeAdapter(Gson gson) {
            return new AutoValue_RestaurantDetail_Contact.GsonTypeAdapter(gson);
        }

        @SerializedName("phone")
        public abstract String getPhone();

        @SerializedName("twitter")
        public abstract String getTwitter();

        @SerializedName("facebookName")
        public abstract String getFacebookName();

    }

    @AutoValue
    public static abstract class Location {

        public static TypeAdapter<Location> typeAdapter(Gson gson) {
            return new AutoValue_RestaurantDetail_Location.GsonTypeAdapter(gson);
        }

        @SerializedName("address")
        public abstract String getAddress();

        @SerializedName("crossStreet")
        public abstract String getCrossStreet();

    }

}
