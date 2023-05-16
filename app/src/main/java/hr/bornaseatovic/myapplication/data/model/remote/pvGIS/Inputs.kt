package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class Inputs(
    @SerializedName("economic_data")
    val economicData: EconomicData,
    @SerializedName("location")
    val location: Location,
    @SerializedName("meteo_data")
    val meteoData: MeteoData,
    @SerializedName("mounting_system")
    val mountingSystem: MountingSystem,
    @SerializedName("pv_module")
    val pvModule: PvModule
)