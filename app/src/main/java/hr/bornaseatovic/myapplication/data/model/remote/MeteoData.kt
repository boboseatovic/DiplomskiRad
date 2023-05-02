package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class MeteoData(
    @SerializedName("horizon_db")
    val horizonDb: String,
    @SerializedName("meteo_db")
    val meteoDb: String,
    @SerializedName("radiation_db")
    val radiationDb: String,
    @SerializedName("use_horizon")
    val useHorizon: Boolean,
    @SerializedName("year_max")
    val yearMax: Int,
    @SerializedName("year_min")
    val yearMin: Int
)