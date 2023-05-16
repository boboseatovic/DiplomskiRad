package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class FixedXX(
    @SerializedName("E_d")
    val eD: Double,
    @SerializedName("E_m")
    val eM: Double,
    @SerializedName("E_y")
    val eY: Double,
    @SerializedName("H(i)_d")
    val hiD: Double,
    @SerializedName("H(i)_m")
    val hiM: Double,
    @SerializedName("H(i)_y")
    val hiY: Double,
    @SerializedName("l_aoi")
    val lAoi: Double,
    @SerializedName("l_spec")
    val lSpec: String,
    @SerializedName("l_tg")
    val lTg: Double,
    @SerializedName("l_total")
    val lTotal: Double,
    @SerializedName("SD_m")
    val sDM: Double,
    @SerializedName("SD_y")
    val sDY: Double
)