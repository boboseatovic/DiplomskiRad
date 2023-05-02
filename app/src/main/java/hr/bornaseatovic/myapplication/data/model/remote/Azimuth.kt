package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Azimuth(
    @SerializedName("optimal")
    val optimal: Boolean,
    @SerializedName("value")
    val value: Int
)