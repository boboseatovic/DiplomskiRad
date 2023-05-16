package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class VariablesXX(
    @SerializedName("horizon_db")
    val horizonDb: HorizonDb,
    @SerializedName("meteo_db")
    val meteoDb: MeteoDb,
    @SerializedName("radiation_db")
    val radiationDb: RadiationDb,
    @SerializedName("use_horizon")
    val useHorizon: UseHorizon,
    @SerializedName("year_max")
    val yearMax: YearMax,
    @SerializedName("year_min")
    val yearMin: YearMin
)