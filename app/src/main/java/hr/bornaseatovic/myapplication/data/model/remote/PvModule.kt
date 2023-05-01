package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PvModule(
    @SerialName("peak_power")
    val peakPower: Double,
    @SerialName("system_loss")
    val systemLoss: Double,
    @SerialName("technology")
    val technology: String
)