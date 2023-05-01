package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Slope(
    @SerialName("optimal")
    val optimal: Boolean,
    @SerialName("value")
    val value: Int
)