package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VariablesXXXXX(
    @SerialName("E_d")
    val eD: ED,
    @SerialName("E_m")
    val eM: EM,
    @SerialName("E_y")
    val eY: EY,
    @SerialName("H(i)_d")
    val hiD: HiD,
    @SerialName("H(i)_m")
    val hiM: HiM,
    @SerialName("H(i)_y")
    val hiY: HiY,
    @SerialName("l_aoi")
    val lAoi: LAoi,
    @SerialName("l_spec")
    val lSpec: LSpec,
    @SerialName("l_tg")
    val lTg: LTg,
    @SerialName("l_total")
    val lTotal: LTotal,
    @SerialName("SD_m")
    val sDM: SDM,
    @SerialName("SD_y")
    val sDY: SDY
)