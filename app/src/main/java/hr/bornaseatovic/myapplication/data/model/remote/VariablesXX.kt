package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VariablesXX(
    @SerialName("horizon_db")
    val horizonDb: HorizonDb,
    @SerialName("meteo_db")
    val meteoDb: MeteoDb,
    @SerialName("radiation_db")
    val radiationDb: RadiationDb,
    @SerialName("use_horizon")
    val useHorizon: UseHorizon,
    @SerialName("year_max")
    val yearMax: YearMax,
    @SerialName("year_min")
    val yearMin: YearMin
)