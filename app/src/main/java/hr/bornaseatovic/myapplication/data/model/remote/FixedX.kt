package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FixedX(
    @SerialName("E_d")
    val eD: Double,
    @SerialName("E_m")
    val eM: Double,
    @SerialName("H(i)_d")
    val hiD: Double,
    @SerialName("H(i)_m")
    val hiM: Double,
    @SerialName("month")
    val month: Int,
    @SerialName("SD_m")
    val sDM: Double
)