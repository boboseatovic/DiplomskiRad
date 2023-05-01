package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UseHorizon(
    @SerialName("description")
    val description: String
)