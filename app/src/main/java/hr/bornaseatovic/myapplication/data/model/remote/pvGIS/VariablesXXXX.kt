package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class VariablesXXXX(
    @SerializedName("E_d")
    val eD: ED,
    @SerializedName("E_m")
    val eM: EM,
    @SerializedName("H(i)_d")
    val hiD: HiD,
    @SerializedName("H(i)_m")
    val hiM: HiM,
    @SerializedName("SD_m")
    val sDM: SDM
)