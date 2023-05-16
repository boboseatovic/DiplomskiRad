package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class InputsX(
    @SerializedName("economic_data")
    val economicData: EconomicDataX,
    @SerializedName("location")
    val location: LocationX,
    @SerializedName("meteo_data")
    val meteoData: MeteoDataX,
    @SerializedName("mounting_system")
    val mountingSystem: MountingSystemX,
    @SerializedName("pv_module")
    val pvModule: PvModuleX
)