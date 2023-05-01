package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SystemCost(
    @SerialName("description")
    val description: String,
    @SerialName("units")
    val units: String
)