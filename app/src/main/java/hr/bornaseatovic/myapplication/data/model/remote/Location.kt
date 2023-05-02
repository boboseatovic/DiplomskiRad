package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)