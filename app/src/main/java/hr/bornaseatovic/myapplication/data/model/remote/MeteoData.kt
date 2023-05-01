package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeteoData(
    @SerialName("horizon_db")
    val horizonDb: String,
    @SerialName("meteo_db")
    val meteoDb: String,
    @SerialName("radiation_db")
    val radiationDb: String,
    @SerialName("use_horizon")
    val useHorizon: Boolean,
    @SerialName("year_max")
    val yearMax: Int,
    @SerialName("year_min")
    val yearMin: Int
)