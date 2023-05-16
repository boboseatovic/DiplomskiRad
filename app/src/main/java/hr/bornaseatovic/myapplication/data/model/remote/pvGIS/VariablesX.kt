package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class VariablesX(
    @SerializedName("elevation")
    val elevation: Elevation,
    @SerializedName("latitude")
    val latitude: Latitude,
    @SerializedName("longitude")
    val longitude: Longitude
)