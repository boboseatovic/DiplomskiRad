package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FixedXX(
    @SerialName("E_d")
    val eD: Double,
    @SerialName("E_m")
    val eM: Double,
    @SerialName("E_y")
    val eY: Double,
    @SerialName("H(i)_d")
    val hiD: Double,
    @SerialName("H(i)_m")
    val hiM: Double,
    @SerialName("H(i)_y")
    val hiY: Double,
    @SerialName("l_aoi")
    val lAoi: Double,
    @SerialName("l_spec")
    val lSpec: String,
    @SerialName("l_tg")
    val lTg: Double,
    @SerialName("l_total")
    val lTotal: Double,
    @SerialName("SD_m")
    val sDM: Double,
    @SerialName("SD_y")
    val sDY: Double
)