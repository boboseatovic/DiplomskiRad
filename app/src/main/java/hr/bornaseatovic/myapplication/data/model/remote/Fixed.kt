package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fixed(
    @SerialName("azimuth")
    val azimuth: Azimuth,
    @SerialName("slope")
    val slope: Slope,
    @SerialName("type")
    val type: String
)