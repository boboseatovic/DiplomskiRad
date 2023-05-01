package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InputsX(
    @SerialName("economic_data")
    val economicData: EconomicDataX,
    @SerialName("location")
    val location: LocationX,
    @SerialName("meteo_data")
    val meteoData: MeteoDataX,
    @SerialName("mounting_system")
    val mountingSystem: MountingSystemX,
    @SerialName("pv_module")
    val pvModule: PvModuleX
)