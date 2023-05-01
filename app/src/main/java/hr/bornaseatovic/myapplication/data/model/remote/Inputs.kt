package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Inputs(
    @SerialName("economic_data")
    val economicData: EconomicData,
    @SerialName("location")
    val location: Location,
    @SerialName("meteo_data")
    val meteoData: MeteoData,
    @SerialName("mounting_system")
    val mountingSystem: MountingSystem,
    @SerialName("pv_module")
    val pvModule: PvModule
)