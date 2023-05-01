package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VariablesXXXX(
    @SerialName("E_d")
    val eD: ED,
    @SerialName("E_m")
    val eM: EM,
    @SerialName("H(i)_d")
    val hiD: HiD,
    @SerialName("H(i)_m")
    val hiM: HiM,
    @SerialName("SD_m")
    val sDM: SDM
)