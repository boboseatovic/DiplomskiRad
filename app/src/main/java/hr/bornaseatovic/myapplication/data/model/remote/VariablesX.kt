package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VariablesX(
    @SerialName("elevation")
    val elevation: Elevation,
    @SerialName("latitude")
    val latitude: Latitude,
    @SerialName("longitude")
    val longitude: Longitude
)