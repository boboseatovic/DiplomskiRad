package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fields(
    @SerialName("azimuth")
    val azimuth: AzimuthX,
    @SerialName("slope")
    val slope: SlopeX
)